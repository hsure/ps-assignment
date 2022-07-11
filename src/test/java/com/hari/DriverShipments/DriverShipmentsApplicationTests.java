package com.hari.DriverShipments;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DriverShipmentsApplicationTests {

  @Test
  void contextLoads(ApplicationContext context) {
    assertThat(context).isNotNull();
  }
}
