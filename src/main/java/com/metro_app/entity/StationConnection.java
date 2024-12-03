package com.metro_app.entity;


import com.metro_app.dao.StationConnectionRepository;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="station_connections")
public class StationConnection {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "distance")
    private int distance;

    @Column(name="time")
    private int time;

    @ManyToOne
    @JoinColumn(name="station1_id", referencedColumnName = "id")
    private Station station1;

    @ManyToOne
    @JoinColumn(name="station2_id", referencedColumnName = "id")
    private Station station2;

    public StationConnection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Station getStation1() {
        return station1;
    }

    public void setStation1(Station station1) {
        this.station1 = station1;
    }

    public Station getStation2() {
        return station2;
    }

    public void setStation2(Station station2) {
        this.station2 = station2;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "StationConnection{" +
                "id=" + id +
                ", distance=" + distance +
                ", time=" + time +
                ", station1=" + station1 +
                ", station2=" + station2 +
                '}';
    }


}
