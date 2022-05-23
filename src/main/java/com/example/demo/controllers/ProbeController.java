package com.example.demo.controllers;

import com.example.demo.service.ProbeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("probes")
public class ProbeController {
  @Autowired
  private ProbeService probeService;
}
