package com.example.demo.controllers;

import javax.validation.Valid;

import com.example.demo.dtos.CreatePlanetDto;
import com.example.demo.models.Planet;
import com.example.demo.service.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("planets")
public class PlanetController {
  @Autowired
  private PlanetService planetService;

  @PostMapping
  public ResponseEntity<Planet> create(@RequestBody @Valid CreatePlanetDto planetDto) {
    Planet planet = new Planet(
      planetDto.getWidth(),
      planetDto.getHeight()
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(planetService.save(planet));
  }
}
