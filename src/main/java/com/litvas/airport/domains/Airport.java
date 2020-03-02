package com.litvas.airport.domains;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Airport {

    private Integer capacityOfGarage = 10;

    private volatile Integer availableSeatInGarage = capacityOfGarage;

    private volatile RunwayStatus runwayAvailable = RunwayStatus.AVAILABLE;

    private static Airport airport;

    private Airport() {
    }

    synchronized public static Airport getInstance() {
        if (airport == null) {
            airport = new Airport();
        }
        return airport;
    }

    public void setRunwayAvailable(RunwayStatus runwayAvailable) {
        this.runwayAvailable = runwayAvailable;
        System.out.println("Runway is " + runwayAvailable);
    }
}
