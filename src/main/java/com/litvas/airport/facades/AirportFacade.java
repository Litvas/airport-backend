package com.litvas.airport.facades;

import com.litvas.airport.domains.Airplain;
import org.springframework.stereotype.Component;

@Component
public interface AirportFacade {

    public void startFly(Airplain airplain);

    public void finishFly(Airplain airplain);

}
