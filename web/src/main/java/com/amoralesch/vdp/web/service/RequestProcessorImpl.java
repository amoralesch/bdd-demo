package com.amoralesch.vdp.web.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestProcessorImpl implements RequestProcessor {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public JsonNode processGet(String path) {
        int i = 0;
        ObjectNode root = mapper.createObjectNode();

        for (String part : path.split("/"))
            root.put("" + i++, part);

        return root;
    }

    @Override
    public JsonNode processPost(String path, JsonNode root) {
        String id = root.get("id").textValue();

        if ("123".equals(id))
            requires(root, "first name", "user.firstName");

        return root;
    }

    private void requires(JsonNode root, String field, String fieldPath) {
        JsonNode r = root;

        for (String p : fieldPath.split("\\.")) {
            r = r.get(p);

            if (r == null)
                throw new IllegalArgumentException(field + " may not be null");
        }
    }
}
