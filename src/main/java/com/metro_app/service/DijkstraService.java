package com.metro_app.service;


import com.metro_app.PathDetails;
import com.metro_app.entity.Station;

import java.util.List;

public interface DijkstraService {



    PathDetails findShortestPath(Station startStation, Station endStation);

}
