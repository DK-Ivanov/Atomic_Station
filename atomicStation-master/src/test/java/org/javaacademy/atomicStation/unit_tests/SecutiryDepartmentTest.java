package org.javaacademy.atomicStation.unit_tests;

import org.javaacademy.atomicStation.secutiry.SecutiryDepartment;
import org.javaacademy.atomicStation.station.NuclearStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("france")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SecutiryDepartmentTest {
    @Autowired
    private SecutiryDepartment secutiryDepartment;

    @Test
    void add_getAccidentTest() {
        secutiryDepartment.addAccident();
        Assertions.assertEquals(1, secutiryDepartment.getCountAccidents());
    }

    @Test
    void resetTest() {
        secutiryDepartment.reset();
        Assertions.assertEquals(0, secutiryDepartment.getCountAccidents());
    }
}
