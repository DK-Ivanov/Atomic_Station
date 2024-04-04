package org.javaacademy.atomicStation.economic;

import lombok.NoArgsConstructor;
import org.javaacademy.atomicStation.economic.EconomicDepartment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@NoArgsConstructor
@Profile("france")
public class FranceEconomicDepartment extends EconomicDepartment {
    private final long BILLION = 1_000_000_000;
    @Value("${energy.cost}")
    private double cost;

    @Override
    public BigDecimal computeYearIncomes(long countElectricity) {
        BigDecimal earnedMoney = BigDecimal.ZERO;
        double persents = 1;
        while (true) {
            if (countElectricity > BILLION) {
                countElectricity -= BILLION;
                earnedMoney = earnedMoney.add(BigDecimal.valueOf(BILLION * cost * persents));
                persents -= 0.01;
            } else {
                earnedMoney = earnedMoney.add(BigDecimal.valueOf(countElectricity * cost * persents));
                break;
            }
        }
        return earnedMoney;
    }

}
