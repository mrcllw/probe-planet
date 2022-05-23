package com.example.demo.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.example.demo.dtos.CreateProbeDto;
import com.example.demo.models.Planet;
import com.example.demo.models.Probe;
import com.example.demo.service.PlanetService;
import com.example.demo.service.ProbeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("probes")
public class ProbeController {
  @Autowired
  private ProbeService probeService;

  @Autowired
  private PlanetService planetService;

  @PostMapping
  public ResponseEntity<Probe> create(@RequestBody @Valid CreateProbeDto probeDto) {
    Optional<Planet> planet = planetService.findById(probeDto.getPlanetId());
    if (planet.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Planet not found");
    }

    Probe probe = new Probe(
      probeDto.getX(),
      probeDto.getY(),
      probeDto.getDirection(),
      planet.get()
    );

    Boolean isValidCoordinates = probeService.checkIfCoordinatesIsValid(probe);
    if (!isValidCoordinates) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid coordinates");
    }

    Boolean isOccupiedPosition = probeService.checkIfPositionIsOccupied(probe.getX(), probe.getY());
    if (isOccupiedPosition) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists a probe at this coordinate");
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(probeService.save(probe));
  }
}
