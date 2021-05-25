package com.amoralesch.vdp.web.service;

import com.amoralesch.vdp.web.model.ExternalRequest;
import com.fasterxml.jackson.databind.JsonNode;

public interface ExternalRestApi {
  String post(ExternalRequest info);

  JsonNode post(String url, JsonNode body);
}
