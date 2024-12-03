package com.metro_app.service;

import com.metro_app.dao.StationRepository;
import com.metro_app.entity.Station;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




@Service
public class StationServiceImpl implements StationService{

    private StationRepository stationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StationServiceImpl(StationRepository theStationRepository){
        this.stationRepository = theStationRepository;
    }

    @Override
    public List<Station> findAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station getStationById(int stationId) {

        try {
            return stationRepository.findById(stationId);
        } catch (RuntimeException e) {
            // Handle exception (you can log or rethrow a different exception if needed)
            System.out.println("Error retrieving station: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void saveStation(Station station) {
        stationRepository.save(station);
    }

    @Transactional
    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }


}
