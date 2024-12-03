package com.metro_app.service;

import com.metro_app.entity.Station;
import com.metro_app.entity.StationConnection;

import java.util.List;

public interface StationConnectionService {

    List<StationConnection> getAllConnections();
    public void addStationConnection(Station station1, Station station2, int distance);
    public List<StationConnection> getConnectionsForStation(int stationId);
    public List<StationConnection> findAllConnectionsForStation(Station station);

}
