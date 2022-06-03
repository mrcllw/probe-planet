package com.example.demo.repositories;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.models.Coordinates;
import com.example.demo.models.Probe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProbeRepository extends JpaRepository<Probe, UUID> {
  Optional<Probe> findOneByCoordinates(Coordinates coordinates);
}
