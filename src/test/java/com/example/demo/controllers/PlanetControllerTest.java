package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.example.demo.dtos.CreatePlanetDto;
import com.example.demo.models.Planet;
import com.example.demo.repositories.PlanetRepository;
import com.example.demo.service.PlanetService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureJsonTesters
@WebMvcTest(PlanetController.class)
@Import(PlanetController.class)
@ContextConfiguration(classes = { PlanetService.class, PlanetRepository.class })
public class PlanetControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PlanetRepository planetRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void should_return_200_when_create_a_planet() throws Exception {
    CreatePlanetDto planeDto = new CreatePlanetDto(
      1,
      1
    );

    MockHttpServletResponse response = mockMvc.perform(post("/planets")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(planeDto)))
      .andReturn()
      .getResponse();

    ArgumentCaptor<Planet> argCaptor = ArgumentCaptor.forClass(Planet.class);
    verify(planetRepository).save(argCaptor.capture());
    Planet planet = argCaptor.getValue();

    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    assertEquals(1, planet.getWidth());
    assertEquals(1, planet.getHeight());
  }

  @Test
  public void should_return_400_when_invalid_width_is_provided() throws Exception {
    CreatePlanetDto planetDto = new CreatePlanetDto(
      -1,
      1
    );

    MockHttpServletResponse response = mockMvc.perform(post("/planets")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(planetDto)))
      .andReturn()
      .getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }

  @Test
  public void should_return_400_when_invalid_height_is_provided() throws Exception {
    CreatePlanetDto planetDto = new CreatePlanetDto(
      1,
      -1
    );

    MockHttpServletResponse response = mockMvc.perform(post("/planets")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(planetDto)))
      .andReturn()
      .getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
  }
}
