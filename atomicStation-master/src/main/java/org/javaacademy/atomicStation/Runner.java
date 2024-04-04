package org.javaacademy.atomicStation;

import org.javaacademy.atomicStation.economic.EconomicDepartment;
import org.javaacademy.atomicStation.economic.FranceEconomicDepartment;
import org.javaacademy.atomicStation.economic.MoroccoEconomicDepartment;
import org.javaacademy.atomicStation.station.NuclearStation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Runner {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Runner.class, args);
		NuclearStation station = context.getBean(NuclearStation.class);
		station.start(10);
	}


}
