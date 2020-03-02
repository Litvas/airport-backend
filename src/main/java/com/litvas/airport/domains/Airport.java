package com.litvas.airport.domains;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Setter
@Getter
public class Airport {

    @Value("${airport.capacityOfgarage}")
    private Integer capacityOfGarage;

    @Value("${airport.capacityOfgarage}")
    private volatile Integer availableSeatInGarage;

    @Value("${airport.runwayAvailableDefault}")
    private volatile RunwayStatus runwayAvailable;

    private static Airport airport;

    private Airport() {
    }

    synchronized public static Airport getInstance() {
        if (airport == null) {
            airport = new Airport();
        }
        return airport;
    }
}
