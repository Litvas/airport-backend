package com.litvas.airport.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Airport {

    @Value("${airport.capacityOfgarage}")
    private Integer capacityOfGarage;

    private volatile Integer availableSeatInGarage;

    @Value("${airport.runwayAvailableDefault}")
    private volatile RunwayStatus runwayAvailable;

}
