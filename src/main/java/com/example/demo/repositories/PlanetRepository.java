package com.example.demo.repositories;

import java.util.UUID;

import com.example.demo.models.Planet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, UUID> {}
