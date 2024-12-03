package com.metro_app.service;

import com.metro_app.PathDetails;
import com.metro_app.dao.StationRepository;
import com.metro_app.entity.LineColor;
import com.metro_app.entity.Station;
import com.metro_app.entity.StationConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DijkstrasServiceImpl implements DijkstraService{

    private final StationConnectionService stationConnectionService;
    private final StationService stationService;

    @Autowired
    public DijkstrasServiceImpl(StationConnectionService stationConnectionService, StationService stationService){
        this.stationConnectionService = stationConnectionService;
        this.stationService = stationService;
    }


    @Override
    public PathDetails findShortestPath(Station startStation, Station endStation) {

        if (startStation == null || endStation == null || startStation.equals(endStation)) {
            return new PathDetails(Collections.emptyList(), 0, 0, 0, 0, null);
        }

        Map<Station, Integer> distances = new HashMap<>();
        Map<Station, Station> previousStations = new HashMap<>();
        Set<Station> visited = new HashSet<>();
        PriorityQueue<Station> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialize all stations with Integer.MAX_VALUE, except the startStation
        int i =1;
        for (Station station : stationService.findAllStations()) {

            distances.put(station, Integer.MAX_VALUE);  // Initializing all stations with a high value
        }
        distances.put(startStation, 0);  // Set the distance of the start station to 0

        queue.add(startStation);

        while (!queue.isEmpty()) {
            Station curr = queue.poll();

            if (visited.contains(curr)) {
                continue;
            }
            visited.add(curr);

            if (curr == endStation) break;

            List<StationConnection> connections = stationConnectionService.findAllConnectionsForStation(curr);

            for (StationConnection sc : connections) {
                Station neighbour;
                if (curr == sc.getStation1()) {
                    neighbour = sc.getStation2();
                } else {
                    neighbour = sc.getStation1();
                }

                if (curr.equals(neighbour)) {
                    continue;
                }

                // Make sure distances.get(curr) is not null before adding
                Integer currentDistance = distances.get(curr);
                if (currentDistance != null) {
                    int newDistance = currentDistance + sc.getDistance();

                    Integer neighbourDistance = distances.get(neighbour);
                    if (neighbourDistance != null && newDistance < neighbourDistance) {
                        distances.put(neighbour, newDistance);
                        previousStations.put(neighbour, curr);
                        queue.add(neighbour);
                    }
                }
            }
        }

        List<Station> path = reconstructPath(previousStations, endStation);
        List<Station> interchangeStation = new ArrayList<>();
        int totalInterchanges = calculateInterchanges(path, interchangeStation);
        int interchangeTime = totalInterchanges * 5;

        int totalTime = calculateTime(distances, path, endStation) + interchangeTime;


        Integer endStationDistance = distances.get(endStation);
        if (endStationDistance == null) {
            endStationDistance = Integer.MAX_VALUE;  // or 0 depending on your logic
        }
        int totalCost = calculateTotalCost(endStationDistance);

        return new PathDetails(path, totalTime, endStationDistance, totalInterchanges, totalCost, interchangeStation);
    }



    private List<Station> reconstructPath(Map<Station, Station> previousStations, Station endStation) {
        List<Station> path = new ArrayList<>();
        Station current = endStation;
        while (current != null) {
            path.add(current);
            current = previousStations.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    private int calculateInterchanges(List<Station> path, List<Station> interchangeStations){
      int interchanges =0;

      for(int i =1;i<path.size()-1;i++){

          if(path.get(i).getColors().size()>1 && !checkForSameLine(path.get(i-1),path.get(i+1)) ){
                 interchanges++;
                 interchangeStations.add(path.get(i));
          }
      }
        return interchanges;
    }


    private boolean checkForSameLine(Station station1, Station station2){

        List<LineColor> station1Line = station1.getColors();
        List<LineColor> station2Line = station2.getColors();

        if(station1Line==null || station2Line==null) return false;

        for(LineColor lc : station1Line){
            for(LineColor lc2: station2Line){
                if(lc.getColor().equals(lc2.getColor())) return true;
            }
        }
     return false;
    }


    private int calculateTime( Map<Station,Integer> distances, List<Station> path, Station endStation ){
        if(distances.get(endStation)==null) return -1;
        int tempT = (distances.get(endStation)*40 ) + (120* path.size());
        return (int) Math.ceil((double)tempT/60);
    }

    private int calculateTotalCost(int distance){
       if(distance<=5) return 20;
       int newD = distance-5;
       int div = newD/3;
       int rem = newD%3;
       if(rem!=0) div++;
       int amount =  20 + (div*5);

       return Math.min(amount, 80);



    }





}
