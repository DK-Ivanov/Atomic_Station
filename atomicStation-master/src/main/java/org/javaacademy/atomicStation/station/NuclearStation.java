package org.javaacademy.atomicStation.station;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.atomicStation.economic.EconomicDepartment;
import org.javaacademy.atomicStation.economic.FranceEconomicDepartment;
import org.javaacademy.atomicStation.secutiry.SecutiryDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NuclearStation {
    private ReactorDepartment reactorDepartment;
    private long generatedEnergy = 0;
    private int accidentCountAllTime = 0;
    private SecutiryDepartment secutiryDepartment;
    private EconomicDepartment economicDepartment;

    public NuclearStation(ReactorDepartment reactorDepartment,
                          SecutiryDepartment secutiryDepartment,
                          EconomicDepartment economicDepartment) {
        this.reactorDepartment = reactorDepartment;
        this.secutiryDepartment = secutiryDepartment;
        this.economicDepartment = economicDepartment;
    }

    private void startYear() {
        log.info("Атомная станция начала работу");
        long producedEnergy = 0;
        for (int i = 0; i < 365; i++) {
            try {
                producedEnergy += reactorDepartment.run();
                reactorDepartment.stop();
            } catch (Exception e) {
                log.info("Внимание! Происходят работы на атомной станции! Электричества нет!");
            }
        }
        log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов", producedEnergy);
        log.info("Количество инцидентов за всю работу станции: " + secutiryDepartment.getCountAccidents());
        log.info("Доход за год составил {}", economicDepartment.computeYearIncomes(producedEnergy), economicDepartment.getCurrency());
        generatedEnergy += producedEnergy;
        secutiryDepartment.reset();
    }

    public void start(int year) {
        log.info("Действие происходит в стране: {}", economicDepartment.getCountryName());
        for (int i = 0; i < year; i++) {
            startYear();
        }
        log.info("Количество инцидентов за всю работу станции: " + accidentCountAllTime);
    }

    public void incrementAccident(int count) {
        accidentCountAllTime += count;
    }

}
