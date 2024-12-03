package com.metro_app;

import com.metro_app.entity.LineColor;
import com.metro_app.entity.Station;
import com.metro_app.service.DijkstraService;
import com.metro_app.service.LineColorService;
import com.metro_app.service.StationConnectionService;
import com.metro_app.service.StationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class MetroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetroApplication.class, args);
	}
}
