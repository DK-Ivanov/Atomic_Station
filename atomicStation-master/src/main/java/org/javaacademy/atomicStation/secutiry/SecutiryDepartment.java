package org.javaacademy.atomicStation.secutiry;

import org.javaacademy.atomicStation.station.NuclearStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SecutiryDepartment {
    private int accidentCountPeriod = 0;
    @Autowired
    @Lazy
    private NuclearStation station;

    public void addAccident() {
        accidentCountPeriod++;
    }

    public int getCountAccidents() {
        return accidentCountPeriod;
    }

    public void reset() {
        station.incrementAccident(accidentCountPeriod);
        accidentCountPeriod = 0;
    }

}
