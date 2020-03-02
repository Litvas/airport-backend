package com.litvas.airport.services.impl;

import com.litvas.airport.dao.AirplainRepository;
import com.litvas.airport.domains.Airplain;
import com.litvas.airport.domains.AirplainStatus;
import com.litvas.airport.services.AirplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplainServiceImpl implements AirplainService {

    @Autowired
    private AirplainRepository airplainRepository;

    @Override
    public Airplain toAir(Airplain airplain) {
        airplain.setAirplainStatus(AirplainStatus.FLIGHT);
        System.out.println("Airplain '" + airplain.getId() + "' moved to air");
        return airplain;
    }

    @Override
    public Airplain toRunway(Airplain airplain) {
        airplain.setAirplainStatus(AirplainStatus.RUNWAY);
        System.out.println("Airplain '" + airplain.getId() + "' moved to " + airplain.getAirplainStatus().name());
        return airplain;
    }

    @Override
    public Airplain toHangar(Airplain airplain) {
        airplain.setAirplainStatus(AirplainStatus.HANGAR);
        System.out.println("Airplain '" + airplain.getId() + "' moved to " + airplain.getAirplainStatus().name());
        return airplain;
    }

    @Override
    public List<Airplain> getAllAirplains() {
        return airplainRepository.findAll();
    }

}
