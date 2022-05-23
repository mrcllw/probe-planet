package com.example.demo.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreatePlanetDto {
  @NotNull
  @Min(1)
  @Max(5)
  private int width;

  @NotNull
  @Min(1)
  @Max(5)
  private int height;

  public CreatePlanetDto() {}

  public CreatePlanetDto(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
