package com.amoralesch.vdp.web.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private final String path;

    private final String verb;

    private String forwardTo;

    private final List<Field> requestFields = new ArrayList();

    private final List<Field> responseFields = new ArrayList();

    public Rule(String verb, String path) {
        this.verb = verb;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getVerb() {
        return verb;
    }

    public void setForwardTo(String forwardTo) {
        this.forwardTo = forwardTo;
    }

    public String getForwardTo() {
        return forwardTo;
    }

    public void addRequestField(Field f) {
        requestFields.add(f);
    }

    public void addResponseField(Field f) {
        responseFields.add(f);
    }

    public void validateFields(JsonNode body) {
        for (Field f : requestFields) {
            JsonNode node = getNode(f.getName(), body);

            if (f.isRequired() && node == null)
                throw new IllegalArgumentException(f.getDescription() + " may not be null");

            if (node == null)
                continue;

            if (node.textValue().length() > f.getMaxLen())
                throw new IllegalArgumentException(
                    f.getDescription() + " may not be longer than " + f.getMaxLen() + " characters");
        }
    }

    public void mapRequest(ObjectNode target, JsonNode source) {
        for (Field f : requestFields) {
            JsonNode node = getNode(f.getName(), source);

            if (node == null)
                continue;

            putValue(target, node.textValue(), f.getMapTo());
        }
    }

    public JsonNode mapResponse(ObjectNode response, JsonNode body) {
        for (Field f : responseFields) {
            JsonNode node = getNode(f.getName(), body);

            if (f.isRequired() && node == null)
                throw new IllegalArgumentException("'" + f.getDescription() + "' field is missing from response");

            if (!f.getShouldBePresent()) {
                if (node != null)
                    throw new IllegalArgumentException("external API replied with: '" + f.getErrorMessageGenerator().apply(body) + "'");

                continue;
            }

            if (f.getMustEqual() != null && !f.getMustEqual().equals(node.textValue()))
                throw new IllegalArgumentException("'" + f.getDescription() + "' field has unexpected response value");

            putValue(response, node.textValue(), f.getMapTo());
        }

        return response;
    }

    private JsonNode getNode(String path, JsonNode root) {
        JsonNode r = root;

        for (String p : path.split("\\.")) {
            r = r.get(p);

            if (r == null)
                return null;
        }

        return r;
    }

    private void putValue(ObjectNode body, String value, String fieldPath) {
        if (value == null)
            return;

        String[] path = fieldPath.split("\\.");

        ObjectNode node = body;
        for (int i = 0; i < path.length - 1; i ++)
            if (body.get(path[i]) == null)
                node = body.putObject(path[i]);
            else
                node = (ObjectNode) body.get(path[i]);

        node.put(path[path.length - 1], value);
    }
}
