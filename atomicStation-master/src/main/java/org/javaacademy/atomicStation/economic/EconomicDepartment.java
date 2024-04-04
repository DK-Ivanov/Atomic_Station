package org.javaacademy.atomicStation.economic;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public abstract class EconomicDepartment {
    @Getter
    @Value("${country.name}")
    private String countryName;
    @Getter
    @Value("${energy.currency}")
    private String currency;
    public abstract BigDecimal computeYearIncomes(long countElectricity);
}
