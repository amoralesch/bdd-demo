package com.amoralesch.vdp.web.controller;

import com.amoralesch.vdp.web.model.CustomRequest;
import com.amoralesch.vdp.web.model.ExternalRequest;
import com.amoralesch.vdp.web.service.ExternalRestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/ext-apply",
  produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplexApplyController {
  @Autowired
  private ExternalRestApi api;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> apply(@RequestBody CustomRequest request) {
    return null;
  }
}
