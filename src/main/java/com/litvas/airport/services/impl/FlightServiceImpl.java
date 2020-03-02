package com.litvas.airport.services.impl;

import com.litvas.airport.domains.Airplain;
import com.litvas.airport.domains.Airport;
import com.litvas.airport.domains.RunwayStatus;
import com.litvas.airport.services.AirplainService;
import com.litvas.airport.services.AirportService;
import com.litvas.airport.services.FlightService;
import com.litvas.airport.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private AirplainService airplainService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private TimeUtils timeUtils;

    @Override
    public void fly(Airplain airplain) {
        airplainService.toAir(airplain);
        airportService.changeRunwayStatus(RunwayStatus.AVAILABLE);
        timeUtils.addWaiting();
        if (airportService.getRunwayStatus().equals(RunwayStatus.AVAILABLE)) {
            airportService.changeRunwayStatus(RunwayStatus.NOT_AVAILABLE);
            airplainService.toRunway(airplain);
            timeUtils.addWaiting();
        } else {
            System.out.println("Runway is busy. Flight of airplain " + airplain.getId() + " is continue");
            fly(airplain);
        }
    }

}
