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
    Map<String, String> response = new HashMap<>();

    response.put("Works?", "Yes");
    response.put("Response", api.post(toExternal(request)));

    return response;
  }

  private ExternalRequest toExternal(CustomRequest request)
  {
    ExternalRequest r = new ExternalRequest();

    r.setId(request.getId());
    r.setFullName(request.getFirstName() + " " + request.getLastName());

    return r;
  }
}
