package org.javaacademy.atomicStation;

import org.javaacademy.atomicStation.station.NuclearStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest()
@ActiveProfiles("france")
class RunnerFranceTests {
	@Autowired
	NuclearStation station;
	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(() -> station.start(2));
	}

}
