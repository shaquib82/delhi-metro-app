package com.metro_app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="name")
    private String name;


    @Column(name="station_code")
    private String stationCode;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "station_line_colors",
            joinColumns = @JoinColumn(name="station_id"),
            inverseJoinColumns = @JoinColumn(name="color_id")
    )
    private List<LineColor> colors;

    public Station() {
    }

    public Station(Integer id, String name, String stationCode, List<LineColor> colors) {
        this.id = id;
        this.name = name;
        this.stationCode = stationCode;
        this.colors = colors;
    }

    public Station(String name, String stationCode, List<LineColor> colors) {
        this.name = name;
        this.stationCode = stationCode;
        this.colors = colors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LineColor> getColors() {
        if(colors==null) return new ArrayList<>();
        return colors;
    }

    public List<String> getLineColorsString(){
        if(colors.isEmpty()) return new ArrayList<>();
        List<String> lineColors = new ArrayList<>();

        for(LineColor lc : colors){
            lineColors.add(lc.getColor());
        }

        return lineColors;
    }

    public void setColors(List<LineColor> colors) {

        if(this.colors==null) this.colors=new ArrayList<>();
        this.colors.clear();
        this.colors = colors;
        this.colors.addAll(colors);
    }

    public void addColor(LineColor color){
        if(colors==null) colors=new ArrayList<>();
        if(!colors.contains(color)) {
            colors.add(color);
            color.getStations().add(this);
        }
    }
    public void removeColor(LineColor color){

        if (colors != null && colors.contains(color)) {
            colors.remove(color);
            color.getStations().remove(this); // Maintaining bidirectional relationship
        }
    }


    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stationCode='" + stationCode + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Ensure same class
        Station station = (Station) o;
        return id == station.id; // Compare by id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Generate hash code using id
    }




}
