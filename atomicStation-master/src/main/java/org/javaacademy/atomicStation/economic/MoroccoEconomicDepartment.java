package org.javaacademy.atomicStation.economic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("morocco")
public class MoroccoEconomicDepartment extends EconomicDepartment {
    @Value("${energy.cost.low}")
    private int costLow;
    @Value("${energy.cost.high}")
    private int costHigh;
    private final long FIVE_BILLIONS = 5_000_000_000l;
    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal earnedMoney = BigDecimal.ZERO;
        if(countElectricity > FIVE_BILLIONS) {
            earnedMoney = earnedMoney.add(BigDecimal.valueOf(FIVE_BILLIONS * costLow + (countElectricity - FIVE_BILLIONS) * costHigh));
        } else {
            earnedMoney = earnedMoney.add(BigDecimal.valueOf(FIVE_BILLIONS * costLow));
        }
        return earnedMoney;
    }
}
