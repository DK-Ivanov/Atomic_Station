package org.javaacademy.atomicStation.station;

import org.javaacademy.atomicStation.secutiry.SecutiryDepartment;
import org.javaacademy.atomicStation.staff.ReactorStatus;
import org.javaacademy.atomicStation.staff.exceptions.NuclearFuelIsEmptyException;
import org.javaacademy.atomicStation.staff.exceptions.ReactorWorkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReactorDepartment {
    private ReactorStatus status = ReactorStatus.DISABLED;
    private int startsCount = 0;
    @Autowired
    private SecutiryDepartment secutiryDepartment;

    public long run() throws Exception {
        if(status == ReactorStatus.IS_RUNNING) {
            secutiryDepartment.addAccident();
            throw new ReactorWorkException("Реактор уже работает");
        }
        startsCount++;
        if (startsCount == 100) {
            startsCount = 0;
            secutiryDepartment.addAccident();
            throw new NuclearFuelIsEmptyException();
        }
        status = ReactorStatus.IS_RUNNING;
        return 10_000_000;
    }

    public void stop() throws ReactorWorkException {
        if (status == ReactorStatus.DISABLED) {
            secutiryDepartment.addAccident();
            throw new ReactorWorkException("Реактор уже выключен");
        }
        status = ReactorStatus.DISABLED;
    }
}
