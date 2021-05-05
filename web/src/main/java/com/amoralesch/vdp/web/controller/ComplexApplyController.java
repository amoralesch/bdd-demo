package com.amoralesch.vdp.web.controller;

import com.amoralesch.vdp.web.model.CustomRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/ext-apply",
  produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplexApplyController {
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> apply(@RequestBody CustomRequest request) {
    Map<String, String> response = new HashMap<>();

    response.put("Works?", "Yes");
    response.put("ID", request.getId());
    response.put("FullName", request.getFirstName() + " " +
      request.getLastName());

    return response;
  }
}
