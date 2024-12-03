package com.metro_app;

import com.metro_app.entity.Station;

import java.util.List;

public class PathDetails {

    private List<Station> path;
    private int time;
    private int distance;
    private int interchanges;
    private int cost;
    private List<Station> interchangeStations;

    public PathDetails(List<Station> stations, int time, int distance, int interchanges, int cost, List<Station> interchangeStations) {
        this.path = stations;
        this.time = time;
        this.distance = distance;
        this.interchanges = interchanges;
        this.cost = cost;
        this.interchangeStations = interchangeStations;
    }

    public List<Station> getPath() {
        return path;
    }

    public int getTime() {
        return time;
    }

    public int getDistance() {
        return distance;
    }

    public int getInterchanges() {
        return interchanges;
    }

    public int getCost() {
        return cost;
    }
    public List<Station> getInterchangeStations() {
        return interchangeStations;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Shortest Path: ");
        for (Station station : path) {
            result.append(station.getName());
            if (interchangeStations.contains(station)) {
                result.append(" (CHANGE LINE)");
            }
            result.append(" -> ");
        }
        result.delete(result.length() - 4, result.length()); // Remove last arrow
        result.append("\nTotal Distance: ").append(distance).append(" km");
        result.append("\nTotal Time: ").append(time).append(" minutes");
        result.append("\nTotal Cost: ₹").append(cost);
        result.append("\nTotal Interchanges: ").append(interchanges);
        return result.toString();
    }
}
