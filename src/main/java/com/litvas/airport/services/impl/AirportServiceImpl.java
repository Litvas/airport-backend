package com.litvas.airport.services.impl;

import com.litvas.airport.domains.Airport;
import com.litvas.airport.domains.RunwayStatus;
import com.litvas.airport.services.AirportService;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceImpl implements AirportService {

    @Override
    public RunwayStatus getRunwayStatus() {
        return Airport
                .getInstance()
                .getRunwayAvailable();
    }

    @Override
    public synchronized void changeRunwayStatus(RunwayStatus newStatus) {
        Airport.getInstance().setRunwayAvailable(newStatus);
    }
}
