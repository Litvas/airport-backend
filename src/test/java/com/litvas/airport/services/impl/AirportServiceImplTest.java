package com.litvas.airport.services.impl;

import com.litvas.airport.domains.Airport;
import com.litvas.airport.domains.RunwayStatus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class AirportServiceImplTest {

    private static final RunwayStatus RUNWAY_AVAILABLE = RunwayStatus.AVAILABLE;
    private static final RunwayStatus RUNWAY_NOT_AVAILABLE = RunwayStatus.NOT_AVAILABLE;

    @InjectMocks
    private AirportServiceImpl testedEntry = new AirportServiceImpl();

    @Test
    void shouldReturnRunwayStatus() {
        assertThat(testedEntry.getRunwayStatus()).isInstanceOf(RUNWAY_AVAILABLE.getClass());
    }

    @Test
    void changeRunwayStatusToNotAvailable() {
        testedEntry.changeRunwayStatus(RUNWAY_NOT_AVAILABLE);
        assertThat(Airport.getInstance().getRunwayAvailable()).isEqualTo(RUNWAY_NOT_AVAILABLE);
    }

    @Test
    void changeRunwayStatusToAvailable() {
        testedEntry.changeRunwayStatus(RUNWAY_AVAILABLE);
        assertThat(Airport.getInstance().getRunwayAvailable()).isEqualTo(RUNWAY_AVAILABLE);
    }
}
