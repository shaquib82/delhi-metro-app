package com.metro_app.service;

import com.metro_app.entity.Station;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;


public interface StationService {

     List<Station> findAllStations();
     Station getStationById(int stationId);
     void saveStation(Station station);

     public void flushAndClear();

}
