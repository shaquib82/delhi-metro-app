package com.metro_app.controller;


import com.metro_app.PathDetails;
import com.metro_app.entity.Station;
import com.metro_app.entity.StationConnection;
import com.metro_app.service.DijkstraService;
import com.metro_app.service.StationConnectionService;
import com.metro_app.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@Controller
public class MetroController {

    private final StationService stationService;
    private final DijkstraService dijkstraService;

    @Autowired
    public MetroController(StationService theStationService, DijkstraService dijkstraService){
        this.stationService = theStationService;
        this.dijkstraService=dijkstraService;

    }

    @GetMapping("/")
    public String home(Model theModel){
        theModel.addAttribute("title","Welcome to the Metro App");
        return "home";
    }

    @GetMapping("/stations")
    public String listAllStations(Model theModel){

     List<Station> stations = stationService.findAllStations();

        stations.sort(Comparator.comparing(Station::getName));

        if (stations.isEmpty()) {
            System.out.println("No stations found in the database.");
        }
     theModel.addAttribute("title","ALL STATIONS");
     theModel.addAttribute("stations", stations);

     return "stations";
    }

    @GetMapping("/metro-map")
    public String showMetroMap(Model model) {
        return "metro-map";
    }

    @GetMapping("/plan-route")
    public String planRoute(Model theModel){

        List<Station> stations = stationService.findAllStations();

        stations.sort(Comparator.comparing(Station::getName));

        theModel.addAttribute("stations", stations);

        return "plan-route";
    }


    @PostMapping("/find-route")
    public String findRoute(@RequestParam("startStation") int startStationId, @RequestParam("destinationStation") int destinationStationId,
    Model theModel){

     PathDetails pd = dijkstraService.findShortestPath(stationService.getStationById(startStationId),stationService.getStationById(destinationStationId));

        List<Station> stations = stationService.findAllStations();

        stations.sort(Comparator.comparing(Station::getName));

        theModel.addAttribute("stations", stations);

     theModel.addAttribute("pathDetails",pd);

     theModel.addAttribute("station1", stationService.getStationById(startStationId));
     theModel.addAttribute("station2",stationService.getStationById(destinationStationId));

     return "plan-route";

    }

    @GetMapping("/fares")
    public String checkFares(Model theModel){
        List<Station> st = stationService.findAllStations();

        List<Station> stations = stationService.findAllStations();

        stations.sort(Comparator.comparing(Station::getName));

        theModel.addAttribute("stations", stations);
     return "check-fares";
    }

    @PostMapping("/check-fare")
    public String checkFare(Model theModel, @RequestParam("startStation") int startStationId, @RequestParam("destinationStation") int destinationStationId ){

        Station st1 = stationService.getStationById(startStationId);
        Station st2 = stationService.getStationById(destinationStationId);

        PathDetails pd = dijkstraService.findShortestPath(st1,st2);


        theModel.addAttribute("pathDetails",pd);

        List<Station> stations = stationService.findAllStations();

        stations.sort(Comparator.comparing(Station::getName));

        theModel.addAttribute("stations", stations);

        theModel.addAttribute("station1",st1);
        theModel.addAttribute("station2",st2);

        return "check-fares";
    }




}
