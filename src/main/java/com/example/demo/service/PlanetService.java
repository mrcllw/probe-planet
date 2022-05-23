package com.example.demo.service;

import javax.transaction.Transactional;

import com.example.demo.models.Planet;
import com.example.demo.repositories.PlanetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {
  @Autowired
  private PlanetRepository planetRepository;

  @Transactional
  public Planet save(Planet planet) {
    return planetRepository.save(planet);
  }
}
