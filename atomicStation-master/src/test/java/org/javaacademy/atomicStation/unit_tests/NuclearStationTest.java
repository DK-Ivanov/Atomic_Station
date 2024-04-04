package org.javaacademy.atomicStation.unit_tests;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.atomicStation.economic.EconomicDepartment;
import org.javaacademy.atomicStation.secutiry.SecutiryDepartment;
import org.javaacademy.atomicStation.staff.ReactorStatus;
import org.javaacademy.atomicStation.station.NuclearStation;
import org.javaacademy.atomicStation.station.ReactorDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@SpringBootTest
@Slf4j
public class NuclearStationTest {
    @MockBean
    private ReactorDepartment reactorDepartment;
    @MockBean
    private SecutiryDepartment secutiryDepartment;
    @MockBean
    private EconomicDepartment economicDepartment;
    @Autowired
    private NuclearStation nuclearStation;

    @Test
    @SneakyThrows
    void srart() {
        Mockito.when(reactorDepartment.run()).thenReturn(10l);
        nuclearStation.incrementAccident(1);
        nuclearStation.start(1);
        long generatedEnergy = 0;
        Field field_generatedEnergy = null;
        int accidentCountAllTime = 0;
        Field field_accidentCountAllTime = null;
        try {
            field_generatedEnergy = nuclearStation.getClass().getDeclaredField("generatedEnergy");
            field_generatedEnergy.setAccessible(true);
            generatedEnergy = (long) field_generatedEnergy.get(nuclearStation);
            field_accidentCountAllTime = nuclearStation.getClass().getDeclaredField("accidentCountAllTime");
            field_accidentCountAllTime.setAccessible(true);
            accidentCountAllTime = (int) field_accidentCountAllTime.get(nuclearStation);
        } catch (Exception e) {
            log.error("Не удалось получить поля");
        }
        Assertions.assertEquals(1, accidentCountAllTime);
        Assertions.assertEquals(3650l, generatedEnergy);
    }

}
