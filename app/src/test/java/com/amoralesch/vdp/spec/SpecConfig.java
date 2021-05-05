package com.amoralesch.vdp.spec;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import okhttp3.mockwebserver.MockWebServer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.amoralesch.vdp.web.Application;

@Configuration
@ComponentScan
@Import({Application.class})
public class SpecConfig {
  private static final Logger log = getLogger(SpecConfig.class);

  @Bean
  @Description("Mock MVC for REST API testing")
  public MockMvc mockMvc(WebApplicationContext ctx)
  {
    log.trace("entering");

    return webAppContextSetup(ctx).build();
  }

  @Bean
  public MockWebServer fakeRestApi()
  {
    return new MockWebServer();
  }

  @Bean
  @Qualifier("fakeExternalApi")
  public String fakeExternalApi(MockWebServer server)
  {
    return server.url("localhost/").toString();
  }
}

