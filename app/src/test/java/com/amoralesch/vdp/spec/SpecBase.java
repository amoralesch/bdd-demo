package com.amoralesch.vdp.spec;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.concordion.api.FullOGNL;
import org.concordion.api.option.ConcordionOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@RunWith(SpecRunner.class)
@SpringBootTest(classes = SpecConfig.class, webEnvironment = RANDOM_PORT)
@TestPropertySource("/test.properties")
@ActiveProfiles("test")
@ConcordionOptions(declareNamespaces = {
  "ext", "urn:concordion-extensions:2010"
})
@FullOGNL
public abstract class SpecBase {
}
