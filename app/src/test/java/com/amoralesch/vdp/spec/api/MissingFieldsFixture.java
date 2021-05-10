package com.amoralesch.vdp.spec.api;

import org.concordion.api.MultiValueResult;

public class MissingFieldsFixture extends ApiBase {
  public MultiValueResult http(String method, String uri, String body)
    throws Exception
  {
    return null;
//    return http(method, uri, body, null);
  }
}
