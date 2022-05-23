package com.example.demo.enums;

public enum DirectionEnum {
  NORTH("N"),
  EAST("E"),
  SOUTH("S"),
  WEST("W");

  private String label;

  private DirectionEnum(String label) {
    this.label = label;
  }

  public static DirectionEnum valueOfLabel(String label) {
    for (DirectionEnum direction : values()) {
      if (direction.label.equals(label)) {
        return direction;
      }
    }
    return null;
  }
}
