package com.litvas.airport.services;

import com.litvas.airport.domains.Airplain;
import org.springframework.stereotype.Service;

@Service
public interface FlightService {

    public void fly(Airplain airplain);

}
