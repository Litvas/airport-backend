package com.litvas.airport.services.impl;

import com.litvas.airport.domains.Airplain;
import com.litvas.airport.domains.Airport;
import com.litvas.airport.domains.RunwayStatus;
import com.litvas.airport.services.AirplainService;
import com.litvas.airport.services.AirportService;
import com.litvas.airport.utils.TimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceImplTest {

    private static final Long AIRPLAIN_ID = 1L;
    private static final RunwayStatus RUNWAY_AVAILABLE = RunwayStatus.AVAILABLE;
    private static final RunwayStatus RUNWAY_NOT_AVAILABLE = RunwayStatus.NOT_AVAILABLE;

    private Airplain airplain = new Airplain();

    @InjectMocks
    private FlightServiceImpl testedEntry = new FlightServiceImpl();

    @Mock
    private AirplainService airplainService;

    @Mock
    private AirportService airportService;

    @Mock
    private TimeUtils timeUtils;

    @Before
    public void setUp() {
        airplain.setId(AIRPLAIN_ID);
        doReturn(airplain).when(airplainService).toAir(airplain);
    }

    @Test
    public void workForAvailableRumway() {
        doReturn(RUNWAY_AVAILABLE).when(airportService).getRunwayStatus();
        testedEntry.fly(airplain);

        verify(airplainService, times(1)).toAir(airplain);
        verify(airplainService, times(1)).toRunway(airplain);
        verify(airportService, times(2)).changeRunwayStatus(any(RunwayStatus.class));
        verify(airportService, times(1)).getRunwayStatus();
        verify(timeUtils, times(2)).addWaiting();
    }
}
