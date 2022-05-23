package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.example.demo.enums.CommandEnum;
import com.example.demo.enums.DirectionEnum;
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

  public Optional<Probe> findById(UUID id) {
    return probeRepository.findById(id);
  }

  public Probe move(Probe probe, String commands) {
    List<CommandEnum> commandList = getCommands(commands);
    DirectionEnum direction = probe.getDirection();
    Map<String, Integer> coordinates = new HashMap<>();
    coordinates.put("x", probe.getX());
    coordinates.put("y", probe.getY());

    for(CommandEnum command: commandList) {
      switch (command) {
        case LEFT:
          direction = turnLeft(direction);
          break;
        case RIGHT:
          direction = turnRight(direction);
          break;
        case MOVE:
          coordinates = moveForward(direction, coordinates.get("x"), coordinates.get("y"));
          break;
      }
    }

    return new Probe(
      probe.getId(),
      coordinates.get("x"),
      coordinates.get("y"),
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

  private DirectionEnum turnLeft(DirectionEnum direction) {
    DirectionEnum newDirection = direction;

    switch(direction) {
      case NORTH:
        newDirection = DirectionEnum.WEST;
        break;
      case EAST:
        newDirection = DirectionEnum.NORTH;
        break;
      case SOUTH:
        newDirection = DirectionEnum.EAST;
        break;
      case WEST:
        newDirection = DirectionEnum.SOUTH;
        break;
    }

    return newDirection;
  }

  private DirectionEnum turnRight(DirectionEnum direction) {
    DirectionEnum newDirection = direction;

    switch(direction) {
      case NORTH:
        newDirection = DirectionEnum.EAST;
        break;
      case EAST:
        newDirection = DirectionEnum.SOUTH;
        break;
      case SOUTH:
        newDirection = DirectionEnum.WEST;
        break;
      case WEST:
        newDirection = DirectionEnum.NORTH;
        break;
    }

    return newDirection;
  }

  private Map<String, Integer> moveForward(DirectionEnum direction, int x, int y) {
    Map<String, Integer> coordinates = new HashMap<>();
    int newX = x;
    int newY = y;

    switch(direction) {
      case NORTH:
        newY++;
        break;
      case EAST:
        newX++;
        break;
      case SOUTH:
        newY--;
        break;
      case WEST:
        newX--;
        break;
    }

    coordinates.put("x", newX);
    coordinates.put("y", newY);

    return coordinates;
  }
}
