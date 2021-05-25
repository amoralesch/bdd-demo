package com.amoralesch.vdp.web.controller;

import com.amoralesch.vdp.web.service.RequestProcessor;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RequestProcessor processor;

    @GetMapping("/**")
    public JsonNode dynamicController(HttpServletRequest request)
    {
        String rest = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        rest = rest.replace(PATH, "");

        return processor.processGet(rest);
    }

    @PostMapping("/**")
    @ResponseStatus(HttpStatus.CREATED)
    public JsonNode apply(HttpServletRequest request, @RequestBody JsonNode body) {
        String rest = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        rest = rest.replace(PATH, "");

        return processor.processPost(rest, body);
    }
}
