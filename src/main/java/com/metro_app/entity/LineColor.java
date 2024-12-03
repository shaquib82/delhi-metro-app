package com.metro_app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="line_colors")
public class LineColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="color")
    private String color;


    @ManyToMany(mappedBy = "colors", fetch = FetchType.EAGER)
    private List<Station> stations;

    public LineColor(){
    }

    public LineColor(int id, String color, List<Station> stations) {
        this.id = id;
        this.color = color;
        this.stations = stations;
    }

    public LineColor(String color, List<Station> stations) {
        this.color = color;
        this.stations = stations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Station> getStations() {
        if (this.stations==null) return new ArrayList<>();
        return stations;

    }

    public void setStations(List<Station> stations) {
        if(this.stations==null) this.stations = new ArrayList<>();
        this.stations.clear();
        this.stations = stations;

        for (Station station : stations) {
            if (!station.getColors().contains(this)) {
                station.addColor(this);
            }
        }

    }
    public void addStation(Station station){
        if(this.stations==null) this.stations = new ArrayList<>();
        if (!stations.contains(station)) { // Avoid duplicates
            stations.add(station);
            station.addColor(this); // Maintain bidirectional relationship
        }
    }

    public void removeStation(Station station) {
        if (this.stations != null && stations.contains(station)) {
            stations.remove(station); // Remove from current list
            station.getColors().remove(this); // Maintain bidirectional relationship
        }
    }

    @Override
    public String toString() {
        return "LineColor{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", stations=" + stations +
                '}';
    }



}
