package com.litvas.airport.services;

import com.litvas.airport.domains.RunwayStatus;
import org.springframework.stereotype.Service;

@Service
public interface AirportService {

    public RunwayStatus getRunwayStatus();

    public void changeRunwayStatus(RunwayStatus newStatus);

}
