package com.amoralesch.vdp.web.controller;

import com.amoralesch.vdp.web.model.CustomRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping(path = "/api/apply",
  produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplyController {
  @GetMapping
  public Map<String, String> query() {
    return null;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> apply(@RequestBody CustomRequest request) {
    return null;
  }
}
