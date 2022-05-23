package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.demo.enums.DirectionEnum;
import com.example.demo.models.Planet;
import com.example.demo.models.Probe;
import com.example.demo.repositories.ProbeRepository;
import com.example.demo.service.ProbeService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProveServiceTest {
  @Mock
  private ProbeRepository probeRepository;

  @InjectMocks
  private ProbeService probeService;

  @Test
  public void should_return_true_when_receive_a_valid_coordinates() {
    Probe probe = new Probe(
      1,
      1,
      DirectionEnum.NORTH,
      new Planet(5, 5)
    );

    Boolean isValidCoordinates = probeService.checkIfCoordinatesIsValid(probe);
    assertEquals(true, isValidCoordinates);
  }

  @Test
  public void should_return_false_when_receive_a_invalid_coordinates() {
    Probe probe = new Probe(
      1,
      6,
      DirectionEnum.NORTH,
      new Planet(5, 5)
    );

    Boolean isValidCoordinates = probeService.checkIfCoordinatesIsValid(probe);
    assertEquals(false, isValidCoordinates);
  }

  @Test
  public void should_return_true_when_receive_a_probe_and_his_position_is_occupied() {
    when(probeRepository.findOneByXAndY(anyInt(), anyInt())).thenReturn(Optional.of(new Probe()));
    
    Boolean isOccupiedPosition = probeService.checkIfPositionIsOccupied(1, 1);
    assertEquals(true, isOccupiedPosition);
  }

  @Test
  public void should_return_false_when_receive_a_probe_and_his_position_is_not_occupied() {
    when(probeRepository.findOneByXAndY(anyInt(), anyInt())).thenReturn(Optional.empty());

    Boolean isOccupiedPosition = probeService.checkIfPositionIsOccupied(1, 1);
    assertEquals(false, isOccupiedPosition);
  }
}
