package org.javaacademy.atomicStation.unit_tests;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.atomicStation.staff.ReactorStatus;
import org.javaacademy.atomicStation.staff.exceptions.NuclearFuelIsEmptyException;
import org.javaacademy.atomicStation.staff.exceptions.ReactorWorkException;
import org.javaacademy.atomicStation.station.ReactorDepartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Field;

@SpringBootTest
@ActiveProfiles("france")
@Slf4j
class ReactorDepartmentTest {
    @Autowired
    private ReactorDepartment reactorDepartment;

    @BeforeEach
    void reset() {
        ReactorStatus status = null;
        int startsCount = 0;
        Field field_status = null;
        Field field_startCount = null;
        try {
            field_status = reactorDepartment.getClass().getDeclaredField("status");
            field_status.setAccessible(true);
            status = (ReactorStatus) field_status.get(reactorDepartment);

            field_startCount = reactorDepartment.getClass().getDeclaredField("startsCount");
            field_startCount.setAccessible(true);
            startsCount = (int) field_startCount.get(reactorDepartment);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Не удалось получить статус или количество запусков!");
        }
        if (status == ReactorStatus.IS_RUNNING) {
            try {
                field_status.set(reactorDepartment, (ReactorStatus) ReactorStatus.DISABLED);
            } catch (Exception e) {
                log.error("Не удалось изменить статус!");
            }
        }
        if (startsCount != 0) {
            try {
                field_startCount.set(reactorDepartment, (int) 0);
            } catch (IllegalAccessException e) {
                log.error("Не удалось изменить количество запусков!");
            }
        }
    }

    @Test
    @SneakyThrows
    void runReturn() {
        Assertions.assertEquals(10_000_000l, reactorDepartment.run());
    }

    @Test
    void runRWException() {
        Assertions.assertDoesNotThrow(() -> reactorDepartment.run());
        Assertions.assertThrows(ReactorWorkException.class, () -> reactorDepartment.run());
    }


    @Test
    @SneakyThrows
    void runNFException() {
        for (int i = 0; i < 99; i++) {
            Assertions.assertDoesNotThrow(() -> reactorDepartment.run());
            reactorDepartment.stop();
        }
        Assertions.assertThrows(NuclearFuelIsEmptyException.class, () -> reactorDepartment.run());
    }

    @Test
    @SneakyThrows
    void stopRWException() {
        Assertions.assertThrows(ReactorWorkException.class, () -> reactorDepartment.stop());
        reactorDepartment.run();
        Assertions.assertDoesNotThrow(() -> reactorDepartment.stop());
    }

}
