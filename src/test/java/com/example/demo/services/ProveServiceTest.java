package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

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

  @Test
  public void should_change_probe_direction_from_N_To_W_when_receive_the_command_L() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.NORTH,
      new Planet()
    );
    String command = "L";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(DirectionEnum.WEST, movedProbe.getDirection());
  }

  @Test
	public void should_change_probe_direction_from_W_To_S_when_receive_the_command_L() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.WEST,
      new Planet()
    );
    String command = "L";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(DirectionEnum.SOUTH, movedProbe.getDirection());	
	}
	
	@Test
	public void should_change_probe_direction_from_S_To_E_when_receive_the_command_L() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.SOUTH,
      new Planet()
    );
    String command = "L";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(DirectionEnum.EAST, movedProbe.getDirection());	
	}
	
	@Test
	public void should_change_probe_direction_from_E_To_N_when_receive_the_command_L() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.EAST,
      new Planet()
    );
    String command = "L";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(DirectionEnum.NORTH, movedProbe.getDirection());	
	}
	
	@Test
	public void should_change_probe_direction_from_N_To_E_when_receive_the_command_R() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.NORTH,
      new Planet()
    );
    String command = "R";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(DirectionEnum.EAST, movedProbe.getDirection());
	}
	
	@Test
	public void should_change_probe_direction_from_E_To_S_when_receive_the_command_R() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.EAST,
      new Planet()
    );
    String command = "R";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(DirectionEnum.SOUTH, movedProbe.getDirection());	
	}
	
	@Test
	public void should_change_probe_direction_from_S_To_W_when_receive_the_command_R() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.SOUTH,
      new Planet()
    );
    String command = "R";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(DirectionEnum.WEST, movedProbe.getDirection());	
	}
	
	@Test
	public void should_change_probe_direction_from_W_To_N_when_receive_the_command_R() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.WEST,
      new Planet()
    );
    String command = "R";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(DirectionEnum.NORTH, movedProbe.getDirection());	
	}

	@Test
	public void should_change_probe_position_from_1_1_N_To_1_2_N_when_receive_the_command_M() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.NORTH,
      new Planet()
    );
    String command = "M";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(1, movedProbe.getX());
    assertEquals(2, movedProbe.getY());
    assertEquals(DirectionEnum.NORTH, movedProbe.getDirection());
	}
	
	@Test
	public void should_change_probe_position_from_1_1_S_To_1_0_S_when_receive_the_command_M() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.SOUTH,
      new Planet()
    );
    String command = "M";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(1, movedProbe.getX());
    assertEquals(0, movedProbe.getY());
    assertEquals(DirectionEnum.SOUTH, movedProbe.getDirection());
	}
	
	@Test
	public void should_change_probe_position_from_1_1_W_To_0_1_W_when_receive_the_command_M() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.WEST,
      new Planet()
    );
    String command = "M";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(0, movedProbe.getX());
    assertEquals(1, movedProbe.getY());
    assertEquals(DirectionEnum.WEST, movedProbe.getDirection());
	}
	
	@Test
	public void should_change_probe_position_from_1_1_E_To_2_1_E_when_receive_the_command_M() {
    Probe probe = new Probe(
      UUID.randomUUID(),
      1,
      1,
      DirectionEnum.EAST,
      new Planet()
    );
    String command = "M";
    Probe movedProbe = probeService.move(probe, command);
    assertEquals(2, movedProbe.getX());
    assertEquals(1, movedProbe.getY());
    assertEquals(DirectionEnum.EAST, movedProbe.getDirection());
	}
}
