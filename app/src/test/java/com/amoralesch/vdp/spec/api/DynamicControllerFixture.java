package com.amoralesch.vdp.spec.api;

import org.concordion.api.MultiValueResult;

public class DynamicControllerFixture extends ApiBase {
    public MultiValueResult http(String method, String uri)
            throws Exception
    {
        return http(method, uri, null, null);
    }

    public MultiValueResult http(String method, String uri, String body)
            throws Exception
    {
        return http(method, uri, body, null);
    }
}
