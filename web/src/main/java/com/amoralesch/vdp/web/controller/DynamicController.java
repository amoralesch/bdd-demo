package com.amoralesch.vdp.web.controller;

import com.amoralesch.vdp.web.model.CustomRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = DynamicController.PATH,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class DynamicController {
    public static final String PATH = "/api/dyn";

    @GetMapping("/**")
    public Map<String, String> dynamicController(HttpServletRequest request) throws Exception
    {
        int i = 0;
        Map<String, String> response = new HashMap<>();
        String rest = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        rest = rest.replace(PATH, "");

        for (String part : rest.split("/"))
            response.put(i++ + "", part);

        return response;
    }

    @PostMapping("/**")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> apply(HttpServletRequest request, @RequestBody JsonNode body) {
        Map<String, String> response = new HashMap<>();

        String id = body.get("id").textValue();

        if ("123".equals(id))
            requires(body, "first name", "user.firstName");

        return response;
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
