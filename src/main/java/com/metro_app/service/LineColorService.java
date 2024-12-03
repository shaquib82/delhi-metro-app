package com.metro_app.service;


import com.metro_app.entity.LineColor;
import com.metro_app.entity.Station;

import javax.sound.sampled.Line;
import java.util.List;

public interface LineColorService {

    List<LineColor> findAllColors();        // Retrieve all LineColor entities
    LineColor findById(Integer id);
    void saveLineColor(LineColor lineColor);

}
