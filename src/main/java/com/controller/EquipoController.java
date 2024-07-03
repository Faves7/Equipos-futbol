package com.controller;

import com.entity.EquipoEntity;
import com.repository.EquipoRepository;
import com.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/equipos")
@Tag(name = "Equipos de futbol", description = "Operaciones relacionadas con los equipos de futbol, Get, Update, Put y Delete.")
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepository;

    @GetMapping
    @Operation(summary = "Listar todos los equipos", description = "Obtiene una lista de todos los equipos disponibles.")
    public ResponseEntity<List<EquipoEntity>> getAllEquipos() {
        List<EquipoEntity> equipos = equipoRepository.findAll();
        return ResponseEntity.ok(equipos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener equipo por ID", description = "Obtiene un equipo por su ID.")
    public ResponseEntity<?> getEquipoById(@PathVariable Long id) {
        Optional<EquipoEntity> equipo = equipoRepository.findById(id);
        if (equipo.isPresent()) {
            return ResponseEntity.ok(equipo.get());
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Equipo no encontrado");
            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar equipo por nombre", description = "Busca un equipo por su nombre.")
    public ResponseEntity<List<EquipoEntity>> buscarEquipoPorNombre(@RequestParam String nombre) {
        List<EquipoEntity> equipos = equipoRepository.findByNombreContaining(nombre);
        return ResponseEntity.ok(equipos);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo equipo", description = "Crea un nuevo equipo con los datos que se pasan (nombre, liga y pais).")
    public ResponseEntity<?> crearEquipo(@Valid @RequestBody EquipoEntity equipo) {
        try {
            EquipoEntity equipoGuardado = equipoRepository.save(equipo);
            return ResponseEntity.status(201).body(equipoGuardado);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("La solicitud es invalida");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar equipo existente", description = "Actualiza un equipo existente con los datos que se pasan.")
    public ResponseEntity<?> actualizarEquipo(@PathVariable Long id, @Valid @RequestBody EquipoEntity equipoDetalles) {
        Optional<EquipoEntity> equipoOpt = equipoRepository.findById(id);

        if (equipoOpt.isPresent()) {
            EquipoEntity equipoExistente = equipoOpt.get();
            equipoExistente.setNombre(equipoDetalles.getNombre());
            equipoExistente.setLiga(equipoDetalles.getLiga());
            equipoExistente.setPais(equipoDetalles.getPais());
            EquipoEntity equipoActualizado = equipoRepository.save(equipoExistente);
            return ResponseEntity.ok(equipoActualizado);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Equipo no encontrado");
            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar equipo", description = "Elimina un equipo por su ID.")
    public ResponseEntity<?> eliminarEquipo(@PathVariable Long id) {
        Optional<EquipoEntity> equipoOpt = equipoRepository.findById(id);

        if (equipoOpt.isPresent()) {
            equipoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Equipo no encontrado");
            return ResponseEntity.status(404).body(errorResponse);
        }
    }
}
