package com.litvas.airport.dao;

import com.litvas.airport.domains.Airplain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplainRepository extends JpaRepository<Airplain, Long> {
}
