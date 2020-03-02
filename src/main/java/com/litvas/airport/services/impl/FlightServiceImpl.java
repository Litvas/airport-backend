package com.litvas.airport.services.impl;

import com.litvas.airport.domains.Airplain;
import com.litvas.airport.domains.RunwayStatus;
import com.litvas.airport.services.AirplainService;
import com.litvas.airport.services.AirportService;
import com.litvas.airport.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;

public class FlightServiceImpl implements FlightService {

    @Autowired
    private AirplainService airplainService;

    @Autowired
    private AirportService airportService;

    @Override
    public void fly(Airplain airplain) {
        airplainService.toAir(airplain);
        addRandomWaiting();
        if (airportService.getRunwayStatus().equals(RunwayStatus.AVAILABLE)) {
            airportService.changeRunwayStatus(RunwayStatus.NOT_AVAILABLE);
            airplainService.toRunway(airplain);
        } else {
            System.out.println("Runway is busy. Flight of airplain " + airplain.getId() + " is continue");
            fly(airplain);
        }
    }

    private void addRandomWaiting() {
        try {
            int seconds = 5 + (int) (Math.random() * 5);
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
