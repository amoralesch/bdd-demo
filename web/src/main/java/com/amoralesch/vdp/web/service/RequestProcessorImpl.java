package com.amoralesch.vdp.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestProcessorImpl implements RequestProcessor {
    private static final String RULES =
        "{" +
        "  \"rules\" : [" +
        "    {" +
        "      \"entryPoint\" : \"/hola/mundo\"," +
        "      \"verb\" : \"GET\"," +
        "      \"forwardPoint\" : \"/api/testing/{0}\"" +
        "    }," +
        "    {" +
        "      \"entryPoint\" : \"/post/test\"," +
        "      \"verb\" : \"POST\"," +
        "      \"forwardPoint\" : \"/api/pco\"," +
        "      \"fields\" : [" +
        "        {" +
        "          \"name\" : \"id\"," +
        "          \"description\" : \"ID\"," +
        "          \"required\" : false," +
        "          \"maxLen\" : 5," +
        "          \"map\" : \"requestId\"" +
        "        }," +
        "        {" +
        "          \"name\" : \"user.firstName\"," +
        "          \"description\" : \"user's first name\"," +
        "          \"required\" : true," +
        "          \"maxLen\" : 15," +
        "          \"map\" : \"userFirstName\"" +
        "        }," +
        "        {" +
        "          \"name\" : \"user.lastName\"," +
        "          \"description\" : \"user's last name\"," +
        "          \"required\" : false," +
        "          \"maxLen\" : 15," +
        "          \"map\" : \"user.lastName\"" +
        "        }" +
        "      ]," +
        "      \"response\" : [" +
        "        {" +
        "          \"name\" : \"external\"," +
        "          \"description\" : \"external\"," +
        "          \"required\" : false," +
        "          \"map\" : \"connected?\"," +
        "          \"mustBe\" : \"yes\"" +
        "        }," +
        "        {" +
        "          \"name\" : \"response.working\"," +
        "          \"description\" : \"working\"," +
        "          \"required\" : true," +
        "          \"map\" : \"response.value\"" +
        "        }" +
        "      ]" +
        "    }" +
        "  ]" +
        "}";

    private final ObjectMapper mapper;

    private final JsonNode rules;

    @Autowired
    private ExternalRestApi api;

    @Autowired
    public RequestProcessorImpl(ObjectMapper mapper) throws JsonProcessingException {
        this.mapper = mapper;

        rules = mapper.readTree(RULES);
    }

    @Override
    public JsonNode processGet(String path) {
        findRule(path, "GET");

        int i = 0;
        ObjectNode root = mapper.createObjectNode();

        for (String part : path.split("/"))
            root.put("" + i++, part);

        return root;
    }

    private JsonNode findRule(String path, String verb) {
        for (JsonNode node : rules.withArray("rules"))
            if (node.get("entryPoint").textValue().equals(path) && node.get("verb").textValue().equals(verb))
                return node;

        throw new IllegalArgumentException("path " + verb + " " + path + " is not valid");
    }

    @Override
    public JsonNode processPost(String path, JsonNode root) {
        JsonNode rule = findRule(path, "POST");

        hasRequiredFields(rule, root);
        hasCorrectLengths(rule, root);

        JsonNode mappedBody = mapRequest(rule, root);

        return mapResponse(rule, api.post(rule.get("forwardPoint").textValue(), mappedBody));
    }

    private JsonNode mapRequest(JsonNode rule, JsonNode request) {
        ObjectNode body = mapper.createObjectNode();

        for (JsonNode field : rule.withArray("fields")) {
            String value = getRequestValue(request, field.get("name").textValue());

            putValue(body, value, field.get("map").textValue());
        }

        return body;
    }

    private JsonNode mapResponse(JsonNode rule, JsonNode request) {
        ObjectNode body = mapper.createObjectNode();

        for (JsonNode field : rule.withArray("response")) {
            String value = getRequestValue(request, field.get("name").textValue());

            if (field.get("required").booleanValue() && value == null)
                throw new IllegalArgumentException("'" + field.get("description").textValue() +
                    "' field is missing from response");

            JsonNode mustBe = field.get("mustBe");
            if (mustBe != null && !mustBe.textValue().equals(value))
                throw new IllegalArgumentException("'" + field.get("description").textValue() +
                    "' field has unexpected response value");

            putValue(body, value, field.get("map").textValue());
        }

        return body;
    }

    private void putValue(ObjectNode body, String value, String fieldPath) {
        if (value == null)
            return;

        String[] path = fieldPath.split("\\.");

        ObjectNode node = body;
        for (int i = 0; i < path.length - 1; i ++) {
            if (body.get(path[i]) == null)
                node = body.putObject(path[i]);
            else
                node = (ObjectNode) body.get(path[i]);
        }

        node.put(path[path.length - 1], value);
    }

    private void hasRequiredFields(JsonNode rule, JsonNode root) {
        for (JsonNode field : rule.withArray("fields"))
            if (field.get("required").booleanValue())
                requires(root, field.get("description").textValue(), field.get("name").textValue());
    }

    private void hasCorrectLengths(JsonNode rule, JsonNode root) {
        for (JsonNode field : rule.withArray("fields"))
            checkMaxLen(root, field.get("description").textValue(), field.get("name").textValue(),
                field.get("maxLen").intValue());
    }

    private void requires(JsonNode root, String field, String fieldPath) {
        JsonNode r = root;

        for (String p : fieldPath.split("\\.")) {
            r = r.get(p);

            if (r == null)
                throw new IllegalArgumentException(field + " may not be null");
        }
    }

    private String getRequestValue(JsonNode root, String fieldPath) {
        JsonNode r = root;

        for (String p : fieldPath.split("\\.")) {
            r = r.get(p);

            if (r == null)
                return null;
        }

        if (r != null)
            return r.textValue();

        return null;
    }

    private void checkMaxLen(JsonNode root, String field, String fieldPath, int maxLen) {
        JsonNode r = root;

        for (String p : fieldPath.split("\\.")) {
            r = r.get(p);

            if (r == null)
                return;
        }

        if (r != null)
            if (r.textValue().length() > maxLen)
                throw new IllegalArgumentException(field + " may not be longer than " + maxLen + " characters");
    }
}
