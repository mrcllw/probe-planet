package com.example.demo.service;

import com.example.demo.repositories.ProbeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
  @Autowired
  private ProbeRepository probeRepository;
}
