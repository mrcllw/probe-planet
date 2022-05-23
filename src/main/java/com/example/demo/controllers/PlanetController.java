package com.example.demo.controllers;

import com.example.demo.service.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("planets")
public class PlanetController {
  @Autowired
  private PlanetService planetService;
}
