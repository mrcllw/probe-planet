package com.example.demo.enums;

public enum DirectionEnum {
  NORTH("N") {
    @Override
    public DirectionEnum turn(CommandEnum command) {
      switch(command) {
        case LEFT:
          return DirectionEnum.WEST;
        case RIGHT:
          return DirectionEnum.EAST;
        default:
          return this;
      }
    }
  },
  EAST("E") {
    @Override
    public DirectionEnum turn(CommandEnum command) {
      switch(command) {
        case LEFT:
          return DirectionEnum.NORTH;
        case RIGHT:
          return DirectionEnum.SOUTH;
        default:
          return this;
      }
    }
  },
  SOUTH("S") {
    @Override
    public DirectionEnum turn(CommandEnum command) {
      switch(command) {
        case LEFT:
          return DirectionEnum.EAST;
        case RIGHT:
          return DirectionEnum.WEST;
        default:
          return this;
      }
    }
  },
  WEST("W") {
    @Override
    public DirectionEnum turn(CommandEnum command) {
      switch(command) {
        case LEFT:
          return DirectionEnum.SOUTH;
        case RIGHT:
          return DirectionEnum.NORTH;
        default:
          return this;
      }
    }
  };

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

  public abstract DirectionEnum turn(CommandEnum command);
}
