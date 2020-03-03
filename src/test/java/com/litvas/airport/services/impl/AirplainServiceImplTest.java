package com.litvas.airport.services.impl;

import com.litvas.airport.dao.AirplainRepository;
import com.litvas.airport.domains.Airplain;
import com.litvas.airport.domains.AirplainStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AirplainServiceImplTest {

    private static final Long AIRPLAIN_IN_AIR_ID = 1L;
    private static final AirplainStatus AIRPLAIN_IN_AIR_STATUS = AirplainStatus.FLIGHT;
    private static final Long AIRPLAIN_IN_HANGAR_ID = 2L;
    private static final AirplainStatus AIRPLAIN_IN_HANGAR_STATUS = AirplainStatus.HANGAR;
    private static final AirplainStatus AIRPLAIN_IN_RUNWAY_STATUS = AirplainStatus.RUNWAY;

    private Airplain airplainInAir = new Airplain();
    private Airplain airplainInHangar = new Airplain();

    private List<Airplain> airplains = Collections.EMPTY_LIST;

    @InjectMocks
    private AirplainServiceImpl testedEntry = new AirplainServiceImpl();

    @Mock
    private AirplainRepository airplainRepository;

    @Before
    public void setUp() {
        airplainInAir.setId(AIRPLAIN_IN_AIR_ID);
        airplainInAir.setAirplainStatus(AIRPLAIN_IN_AIR_STATUS);
        airplainInHangar.setId(AIRPLAIN_IN_HANGAR_ID);
        airplainInHangar.setAirplainStatus(AIRPLAIN_IN_HANGAR_STATUS);
    }

    @Before
    public void setRules() {
        doReturn(airplains).when(airplainRepository).findAll();
    }

    @Test
    public void shouldReturnAirplainForAir() {
        assertThat(testedEntry.toAir(airplainInAir)).isSameAs(airplainInAir);
    }

    @Test
    public void shouldReturnAirplainForRunway() {
        assertThat(testedEntry.toRunway(airplainInAir)).isSameAs(airplainInAir);
    }

    @Test
    public void shouldReturnAirplainForHangar() {
        assertThat(testedEntry.toRunway(airplainInAir)).isSameAs(airplainInAir);
    }

    @Test
    public void shouldChangeStateToAir() {
        assertThat(testedEntry.toAir(airplainInHangar).getAirplainStatus()).isEqualTo(AIRPLAIN_IN_AIR_STATUS);
    }

    @Test
    public void shouldChangeStateToHangar() {
        assertThat(testedEntry.toHangar(airplainInHangar).getAirplainStatus()).isEqualTo(AIRPLAIN_IN_HANGAR_STATUS);
    }

    @Test
    public void shouldChangeStateToRunway() {
        assertThat(testedEntry.toRunway(airplainInHangar).getAirplainStatus()).isEqualTo(AIRPLAIN_IN_RUNWAY_STATUS);
    }

    @Test
    public void shouldReturnAirplainsList() {
        assertThat(testedEntry.getAllAirplains()).isSameAs(airplains);
    }

    @Test
    public void shouldInvokeRepositoryMethod() {
        testedEntry.getAllAirplains();
        verify(airplainRepository, times(1)).findAll();
    }
}
