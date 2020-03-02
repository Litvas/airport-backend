package com.litvas.airport.services;

import com.litvas.airport.domains.RunwayStatus;

public interface AirportService {

    RunwayStatus getRunwayStatus();

    void changeRunwayStatus(RunwayStatus newStatus);

}
