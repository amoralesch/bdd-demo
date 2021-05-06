package com.amoralesch.vdp.web.service;

import com.amoralesch.vdp.web.model.ExternalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExternalRestApiImpl implements ExternalRestApi {
  private String baseUrl;

  private WebClient webClient;

  @Autowired(required = false)
  @Qualifier("fakeExternalApi")
  public void setBaseUrl(String url)
  {
    baseUrl = url;

    webClient = WebClient.builder().baseUrl(url)
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .build();
  }

  @Autowired
  public ExternalRestApiImpl(@Qualifier("externalApiUrl") String url)
  {
    baseUrl = url;

    webClient = WebClient.builder().baseUrl(url)
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .build();
  }

  @Override
  public String post(ExternalRequest info)
  {
    return webClient.post()
      .uri("/api/pco")
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(Mono.just(info), ExternalRequest.class)
      .retrieve()
      .bodyToMono(String.class)
      .block();
  }
}
