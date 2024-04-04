package org.javaacademy.atomicStation;

import org.javaacademy.atomicStation.station.NuclearStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("morocco")
public class RunnerMoroccoTests {
    @Autowired
    NuclearStation station;
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> station.start(2));
    }
}
