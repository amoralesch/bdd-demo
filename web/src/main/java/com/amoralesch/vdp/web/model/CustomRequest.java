package com.amoralesch.vdp.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class CustomRequest {
  private final String id;

  private final String firstName;

  private String lastName;

  public String getId()
  {
    return id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  @JsonCreator
  public CustomRequest(@JsonProperty("id") String id,
    @JsonProperty("firstName") String firstName)
  {
    this.id = requireNonNull(id, "ID may not be null");
    this.firstName = requireNonNull(firstName, "first name may not be null");
  }
}
