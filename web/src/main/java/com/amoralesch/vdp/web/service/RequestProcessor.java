package com.amoralesch.vdp.web.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface RequestProcessor {
    JsonNode processGet(String path);

    JsonNode processPost(String path, JsonNode root);
}
