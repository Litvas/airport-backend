package com.litvas.airport.facades.impl;

import com.litvas.airport.domains.Airplain;
import com.litvas.airport.domains.AirplainStatus;
import com.litvas.airport.domains.Airport;
import com.litvas.airport.domains.RunwayStatus;
import com.litvas.airport.facades.AirportFacade;
import com.litvas.airport.services.AirplainService;
import com.litvas.airport.services.AirportService;
import com.litvas.airport.services.FlightService;
import com.litvas.airport.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirportFacadeImpl implements AirportFacade {

    private Airplain airplain;

    @Autowired
    private AirplainService airplainService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private TimeUtils timeUtils;

    @Override
    public void startFly(Airplain airplain) {
        if (airportService.getRunwayStatus().equals(RunwayStatus.AVAILABLE)) {
            increaseAvailableSeat();
            airplainService.toRunway(airplain);
        } else {
            System.out.println("Runway is busy. Airplain with id '" + airplain.getId() + "' wait in hangar");
            timeUtils.addWaiting();
            startFly(airplain);
        }
        airportService.changeRunwayStatus(RunwayStatus.NOT_AVAILABLE);
        timeUtils.addWaiting();
        flightService.fly(airplain);
    }

    @Override
    public void finishFly(Airplain airplain) {
        timeUtils.addWaiting();
        if (checkAvailableSeat()) {
            decreaseAvailableSeat();
            airplainService.toHangar(airplain);
        }else {
            System.out.println("No emought seat in garage for fly " + airplain.getId());
            finishFly(airplain);
        }
        airportService.changeRunwayStatus(RunwayStatus.AVAILABLE);
    }

    public void setAirplainForThread(Airplain airplain) {
        if (airplain.getAirplainStatus().equals(AirplainStatus.HANGAR)) {
            if (checkAvailableSeat()) {
                decreaseAvailableSeat();
            } else {
                airplain.setAirplainStatus(AirplainStatus.FLIGHT);
            }
        }
        this.airplain = airplain;
    }

    @Override
    public void run() {
        if (airplain.getAirplainStatus().equals(AirplainStatus.HANGAR)) {
            startFly(airplain);
            finishFly(airplain);
            run();
        }
        if (airplain.getAirplainStatus().equals(AirplainStatus.FLIGHT)) {
            finishFly(airplain);
            run();
        }
    }

    private synchronized Boolean checkAvailableSeat() {
        Boolean result = true;
        if (Airport.getInstance().getAvailableSeatInGarage().equals(0)) {
            result = false;
        }
        return result;
    }

    private synchronized void increaseAvailableSeat() {
        if (!Airport.getInstance().getAvailableSeatInGarage().equals(Airport.getInstance().getCapacityOfGarage())) {
            Integer newAvailableSeats = Airport.getInstance().getAvailableSeatInGarage() + 1;
            Airport.getInstance().setAvailableSeatInGarage(newAvailableSeats);
        }
        System.out.println("Thread " + Thread.currentThread().getName() + "\nCapacity of garage is " + Airport.getInstance().getCapacityOfGarage() + "\nAvailable seat of garage " + Airport.getInstance().getAvailableSeatInGarage());
    }

    private synchronized void decreaseAvailableSeat() {
        Integer newAvailableSeats = Airport.getInstance().getAvailableSeatInGarage() - 1;
        Airport.getInstance().setAvailableSeatInGarage(newAvailableSeats);
        System.out.println("Thread " + Thread.currentThread().getName() + "\nCapacity of garage is " + Airport.getInstance().getCapacityOfGarage() + "\nAvailable seat of garage " + Airport.getInstance().getAvailableSeatInGarage());
    }

}
