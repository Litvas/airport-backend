package com.litvas.airport.facades;

import com.litvas.airport.domains.Airplain;

public interface AirportFacade extends Runnable {

    void startFly(Airplain airplain);

    void finishFly(Airplain airplain);

    void setAirplainForThread(Airplain airplain);

}
