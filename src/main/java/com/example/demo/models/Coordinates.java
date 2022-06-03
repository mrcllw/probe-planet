package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.example.demo.enums.DirectionEnum;

@Embeddable
public class Coordinates {
  @Column(nullable = false)
  private int x;

  @Column(nullable = false)
  private int y;

  public Coordinates() {}

  public Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Coordinates move(DirectionEnum direction) {
    switch(direction) {
      case NORTH:
        this.y++;
        break;
      case EAST:
        this.x++;
        break;
      case SOUTH:
        this.y--;
        break;
      case WEST:
        this.x--;
        break;
    }

    return this;
  }
}
