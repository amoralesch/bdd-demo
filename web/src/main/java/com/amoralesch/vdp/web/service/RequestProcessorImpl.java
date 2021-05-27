package com.amoralesch.vdp.web.service;

import com.amoralesch.vdp.web.logic.Rule;
import com.amoralesch.vdp.web.logic.Rules;
import com.amoralesch.vdp.web.logic.TestRules;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestProcessorImpl implements RequestProcessor {
    private final ObjectMapper mapper;

    private final Rules rules;

    @Autowired
    private ExternalRestApi api;

    @Autowired
    public RequestProcessorImpl(ObjectMapper mapper) {
        this.mapper = mapper;
        rules = new TestRules();
    }

    @Override
    public JsonNode processGet(String path) {
        rules.getRule(path, "GET");

        int i = 0;
        ObjectNode root = mapper.createObjectNode();

        for (String part : path.split("/"))
            root.put("" + i++, part);

        return root;
    }

    @Override
    public JsonNode processPost(String path, JsonNode root) {
        Rule rule = rules.getRule(path, "POST");

        rule.validateFields(root);

        ObjectNode body = mapper.createObjectNode();

        rule.mapRequest(body, root);

        return rule.mapResponse(mapper.createObjectNode(), api.post(rule.getForwardTo(), body));
    }
}
