package com.litvas.airport.runner;

import com.litvas.airport.domains.Airplain;
import com.litvas.airport.facades.AirportFacade;
import com.litvas.airport.services.AirplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportRunner implements CommandLineRunner {

    @Autowired
    private AirplainService airplainService;

    @Autowired
    private AirportFacade airportFacade;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("I have started");
        List<Airplain> airplains = airplainService.getAllAirplains();
        for (Airplain airplain : airplains) {
            airportFacade.setAirplainForThread(airplain);
            Thread thread = new Thread(airportFacade);
            thread.setName("Airplain" + airplain.getId());
            thread.start();
        }
    }
}
