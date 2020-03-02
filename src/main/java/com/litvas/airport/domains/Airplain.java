package com.litvas.airport.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Airplain {

    @Id
    private Long id;

    private AirplainStatus airplainStatus;

}
