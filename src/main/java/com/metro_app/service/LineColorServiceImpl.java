package com.metro_app.service;


import com.metro_app.dao.LineColorRepository;
import com.metro_app.entity.LineColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineColorServiceImpl implements LineColorService {

    private final LineColorRepository lineColorRepository;

    @Autowired
    public LineColorServiceImpl(LineColorRepository lineColorRepository){
        this.lineColorRepository = lineColorRepository;
    }

    @Override
    public List<LineColor> findAllColors() {
        return lineColorRepository.findAll();
    }

    @Override
    public LineColor findById(Integer id) {
        return lineColorRepository.findById(id).orElseThrow(() -> new RuntimeException("LineColor not found with ID: " + id));
    }

    @Override
    public void saveLineColor(LineColor lineColor) {
        lineColorRepository.save(lineColor);
    }
}
