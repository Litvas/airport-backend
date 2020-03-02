package com.litvas.airport.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
public class Airplain {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private AirplainStatus airplainStatus;

}
