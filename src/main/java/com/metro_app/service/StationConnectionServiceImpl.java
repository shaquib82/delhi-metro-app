package com.metro_app.service;

import com.metro_app.dao.StationConnectionRepository;
import com.metro_app.dao.StationRepository;
import com.metro_app.entity.Station;
import com.metro_app.entity.StationConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StationConnectionServiceImpl implements StationConnectionService {

    private final StationConnectionRepository stationConnectionRepository;

    @Autowired
    public StationConnectionServiceImpl(StationConnectionRepository theStationConnectionRepository, StationService stationService){
        this.stationConnectionRepository= theStationConnectionRepository;
    }

    @Override
    public List<StationConnection> getAllConnections() {
        return stationConnectionRepository.findAll();
    }

    @Override
    @Transactional
    public void addStationConnection(Station station1, Station station2, int distance) {

        if (station1 == null || station2 == null) {
            throw new IllegalArgumentException("Both stations must exist.");
        }

        // Create a new StationConnection object
        StationConnection connection = new StationConnection();
        connection.setStation1(station1);
        connection.setStation2(station2);
        connection.setDistance(distance);

        // Calculate time based on the distance (example formula: 120 + 40 * distance)
        int time = 120 + 40 * distance;// Modify the formula as per your requirement
        int minutes = (int)Math.ceil((double)time / 60);
        connection.setTime(minutes);

        // Save the connection with calculated time
        stationConnectionRepository.save(connection);
    }

    @Override
    public List<StationConnection> getConnectionsForStation(int stationId) {
        return stationConnectionRepository.findByStation1IdOrStation2Id(stationId, stationId);
    }

    @Override
    public List<StationConnection> findAllConnectionsForStation(Station station) {
        return stationConnectionRepository.findByStation1OrStation2(station, station);
    }


}
