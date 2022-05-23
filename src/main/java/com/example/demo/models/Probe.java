package com.example.demo.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.enums.DirectionEnum;

@Entity
public class Probe implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private int x;

  @Column(nullable = false)
  private int y;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private DirectionEnum direction;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @JoinColumn(name = "planet_id", referencedColumnName = "id")
  private Planet planet;

  public Probe() {}

  public Probe(int x, int y, DirectionEnum direction, Planet planet) {
    this.x = x;
    this.y = y;
    this.direction = direction;
    this.planet = planet;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public DirectionEnum getDirection() {
    return direction;
  }

  public Planet getPlanet() {
    return planet;
  }
}
