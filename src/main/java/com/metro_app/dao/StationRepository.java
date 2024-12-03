package com.metro_app.dao;

import com.metro_app.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationRepository extends JpaRepository<Station,Integer> {

    Station findById(int stationId);
    List<Station> findAll();

}
