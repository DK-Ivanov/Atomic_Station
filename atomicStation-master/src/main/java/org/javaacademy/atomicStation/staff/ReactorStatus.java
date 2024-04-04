package org.javaacademy.atomicStation.staff;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum ReactorStatus {
    IS_RUNNING(true), DISABLED(false);
    @Getter
    boolean value;
}
