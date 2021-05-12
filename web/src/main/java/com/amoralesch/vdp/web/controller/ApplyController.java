package com.amoralesch.vdp.web.controller;

import java.util.HashMap;
import java.util.Map;

import com.amoralesch.vdp.web.model.CustomRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/apply",
  produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplyController {
  @GetMapping
  public Map<String, String> query() {
    Map<String, String> response = new HashMap<>();

    response.put("One", "Uno");
    response.put("Two", "Dos");

    return response;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> apply(@RequestBody CustomRequest request) {
    String fullName = request.getFirstName() +
      (request.getLastName() == null ? "" : " " + request.getLastName());

    String attributes = "";

    for (Map.Entry<String, String> entry : request.getExtraInfo().entrySet()) {
      if (!attributes.isEmpty())
        attributes += "|";

      attributes += entry.getKey() + ":" + entry.getValue();
    }

    Map<String, String> response = new HashMap<>();

    response.put("Works?", "Yes");
    response.put("ID", request.getId());
    response.put("FullName", fullName);

    if (!attributes.isEmpty())
      response.put("attributes", attributes);

    return response;
  }
}
