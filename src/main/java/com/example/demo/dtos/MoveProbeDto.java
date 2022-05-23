package com.example.demo.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MoveProbeDto {
  @NotBlank
  @Pattern(regexp = "^[LRM]+$", message = "Invalid commands")
  private String commands;

  public String getCommands() {
    return commands;
  }
}
