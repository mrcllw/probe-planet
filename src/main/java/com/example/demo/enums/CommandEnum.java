package com.example.demo.enums;

public enum CommandEnum {
  LEFT("L"),
  RIGHT("R"),
  MOVE("M");

  private String label;

  private CommandEnum(String label) {
    this.label = label;
  }

  public static CommandEnum valueOfLabel(String label) {
    for (CommandEnum command: values()) {
      if (command.label.equals(label)) {
        return command;
      }
    }
    return null;
  }
}
