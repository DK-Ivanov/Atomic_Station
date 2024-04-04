package org.javaacademy.atomicStation.unit_tests;

import org.javaacademy.atomicStation.economic.EconomicDepartment;
import org.javaacademy.atomicStation.economic.FranceEconomicDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("france")
public class FranceEconomicDepartmentTest {
    @Autowired
    EconomicDepartment economicDepartment;
    @Test
    void computeYearIncomesTest() {
       Assertions.assertEquals(economicDepartment.computeYearIncomes(2_000_000_000), BigDecimal.valueOf(995000000));
    }
}
