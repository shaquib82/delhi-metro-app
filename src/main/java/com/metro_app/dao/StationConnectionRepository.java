package com.metro_app.dao;

import com.metro_app.entity.Station;
import com.metro_app.entity.StationConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationConnectionRepository extends JpaRepository<StationConnection,Integer> {
    List<StationConnection> findByStation1IdOrStation2Id(int station1Id, int station2Id);
    List<StationConnection> findByStation1OrStation2(Station station1, Station station2);

}
