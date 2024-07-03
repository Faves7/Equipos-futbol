package com.repository;

import com.entity.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio EquipoRepository: Proporciona operaciones CRUD (Create, Read,
 * Update y Delete) para la entidad EquipoEntity.
 */
@Repository
public interface EquipoRepository extends JpaRepository<EquipoEntity, Long> {

    /**
     * Encuentra todos los equipos cuyo nombre contenga la cadena especificada en la
     * solicitud.
     *
     * @param nombre la cadena que debe estar contenida en el nombre de los equipos
     * @return una lista de equipos cuyos nombres contienen la cadena especificada
     */
    List<EquipoEntity> findByNombreContaining(String nombre);
}
