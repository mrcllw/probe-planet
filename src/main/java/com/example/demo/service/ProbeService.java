package com.example.demo.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.models.Planet;
import com.example.demo.models.Probe;
import com.example.demo.repositories.ProbeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
  @Autowired
  private ProbeRepository probeRepository;

  @Transactional
  public Probe save(Probe probe) {
    return probeRepository.save(probe);
  }

  private Optional<Probe> findByCoordinates(int x, int y) {
    return probeRepository.findOneByXAndY(x, y);
  }

  public Boolean checkIfCoordinatesIsValid(Probe probe) {
    Planet planet = probe.getPlanet();

    Boolean isValidXCoordinate = Math.abs(probe.getX()) <= planet.getWidth();
    Boolean isValidYCoordinate = Math.abs(probe.getY()) <= planet.getHeight();

    return isValidXCoordinate && isValidYCoordinate;
  }

  public Boolean checkIfPositionIsOccupied(int x, int y) {
    Optional<Probe> probe = findByCoordinates(x, y);

    return probe.isPresent();
  }
}
