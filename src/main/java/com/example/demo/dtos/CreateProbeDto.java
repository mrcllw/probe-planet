package com.example.demo.dtos;

import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.demo.enums.DirectionEnum;

public class CreateProbeDto {
  @NotNull
  @Min(-5)
  @Max(5)
  private int x;

  @NotNull
  @Min(-5)
  @Max(5)
  private int y;

  @NotBlank
  @Pattern(regexp = "^(?:N|E|S|W)$", message = "Invalid direction. Must be N(north), E(east), S(south) or W(west)")
  private String direction;

  @NotBlank
  @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Invalid UUID")
  private String planetId;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public DirectionEnum getDirection() {
    return DirectionEnum.valueOfLabel(direction);
  }

  public UUID getPlanetId() {
    return UUID.fromString(planetId);
  }
}
