package com.controller;

import com.entity.EquipoEntity;
import com.repository.EquipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EquipoControllerTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoController equipoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("should return all teams")
    public void testGetAllEquipos() {
        List<EquipoEntity> equipos = new ArrayList<>();
        equipos.add(new EquipoEntity(1L, "Real Madrid", "La Liga", "España"));
        equipos.add(new EquipoEntity(2L, "FC Barcelona", "La Liga", "España"));

        when(equipoRepository.findAll()).thenReturn(equipos);

        ResponseEntity<List<EquipoEntity>> response = equipoController.getAllEquipos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());

        verify(equipoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("should return team's info")
    public void testGetEquipoById() {
        EquipoEntity equipo = new EquipoEntity(1L, "Real Madrid", "La Liga", "España");

        when(equipoRepository.findById(anyLong())).thenReturn(Optional.of(equipo));

        ResponseEntity<?> response = equipoController.getEquipoById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(equipo, response.getBody());

        verify(equipoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("should return an empty list.")
    public void testGetEquipoByIdNotFound() {
        when(equipoRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<?> response = equipoController.getEquipoById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(equipoRepository, times(1)).findById(1L);
    }
}
