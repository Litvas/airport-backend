package com.litvas.airport.facades.impl;

import com.litvas.airport.domains.Airplain;
import com.litvas.airport.domains.AirplainStatus;
import com.litvas.airport.domains.RunwayStatus;
import com.litvas.airport.services.AirplainService;
import com.litvas.airport.services.AirportService;
import com.litvas.airport.services.FlightService;
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
public class AirportFacadeImplTest {

    private static final Long AIRPLAIN_IN_AIR_ID = 1L;
    private static final AirplainStatus AIRPLAIN_IN_AIR_STATUS = AirplainStatus.FLIGHT;
    private static final Long AIRPLAIN_IN_HANGAR_ID = 2L;
    private static final AirplainStatus AIRPLAIN_IN_HANGAR_STATUS = AirplainStatus.HANGAR;
    private static final RunwayStatus RUNWAY_AVAILABLE = RunwayStatus.AVAILABLE;
    private static final RunwayStatus RUNWAY_NOT_AVAILABLE = RunwayStatus.NOT_AVAILABLE;

    private Airplain airplainInAir = new Airplain();
    private Airplain airplainInHangar = new Airplain();

    @InjectMocks
    private AirportFacadeImpl testedEntry;

    @Mock
    private AirplainService airplainService;

    @Mock
    private FlightService flightService;

    @Mock
    private AirportService airportService;

    @Mock
    private TimeUtils timeUtils;

    @Before
    public void setRules() {
        doReturn(airplainInHangar).when(airplainService).toHangar(any(Airplain.class));
    }

    @Before
    public void init() {
        airplainInAir.setId(AIRPLAIN_IN_HANGAR_ID);
        airplainInAir.setAirplainStatus(AirplainStatus.FLIGHT);

        airplainInHangar.setId(AIRPLAIN_IN_HANGAR_ID);
        airplainInHangar.setAirplainStatus(AIRPLAIN_IN_HANGAR_STATUS);
    }

    @Test
    public void shouldInvokeMethodsForRanwayAvailableForFly() {
        doReturn(RUNWAY_AVAILABLE).when(airportService).getRunwayStatus();
        testedEntry.startFly(airplainInHangar);

        verify(airportService, times(1)).getRunwayStatus();
        verify(airportService, times(1)).changeRunwayStatus(any(RunwayStatus.class));
        verify(timeUtils, times(2)).addWaiting();
        verify(airplainService, times(1)).toRunway(airplainInHangar);
        verify(flightService, times(1)).fly(airplainInHangar);
    }

    @Test
    public void shouldInvokeMethodsForRanwayAvailableForFinishFly() {
        testedEntry.finishFly(airplainInHangar);

        verify(airportService, times(1)).changeRunwayStatus(RUNWAY_AVAILABLE);
        verify(airplainService, times(1)).toHangar(any(Airplain.class));
    }
}
