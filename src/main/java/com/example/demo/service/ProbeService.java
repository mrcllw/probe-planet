package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.example.demo.enums.CommandEnum;
import com.example.demo.enums.DirectionEnum;
import com.example.demo.models.Coordinates;
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

  public Boolean checkIfCoordinatesIsValid(Planet planet, Coordinates coordinates) {

    Boolean isValidXCoordinate = Math.abs(coordinates.getX()) <= planet.getWidth();
    Boolean isValidYCoordinate = Math.abs(coordinates.getY()) <= planet.getHeight();

    return isValidXCoordinate && isValidYCoordinate;
  }

  public Boolean checkIfPositionIsOccupied(Coordinates coordinates) {
    Optional<Probe> probe = probeRepository.findOneByCoordinates(coordinates);

    return probe.isPresent();
  }

  public Optional<Probe> findById(UUID id) {
    return probeRepository.findById(id);
  }

  public Probe move(Probe probe, String commands) {
    List<CommandEnum> commandList = getCommands(commands);
    DirectionEnum direction = probe.getDirection();
    Coordinates coordinates = new Coordinates(
      probe.getCoordinates().getX(),
      probe.getCoordinates().getY()
    );

    for(CommandEnum command: commandList) {
      switch (command) {
        case LEFT:
        case RIGHT:
          direction = direction.turn(command);
          break;
        case MOVE:
          coordinates.move(direction);
          break;
      }
    }

    return new Probe(
      probe.getId(),
      coordinates,
      direction,
      probe.getPlanet()
    );
  }

  private List<CommandEnum> getCommands(String commands) {
    List<CommandEnum> commandList = new ArrayList<CommandEnum>();
    String[] commandArray = commands.split("");

    for (String command: commandArray) {
      commandList.add(CommandEnum.valueOfLabel(command));
    }

    return commandList;
  }
}
