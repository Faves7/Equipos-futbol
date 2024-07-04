package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase EquipoEntity: Representa la entidad de un equipo de fútbol
 * en la base de datos, obteniendo los datos de la tabla 'equipos'.
 * En donde dicha tabla tiene los campos 'nombre', 'liga' y 'pais'.
 */
@Entity
@Table(name = "equipos")
@Schema(description = "Entidad que representa un equipo de fútbol.")
public class EquipoEntity {

    /**
     * El identificador único del equipo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "El identificador único del equipo.", example = "1")
    private Long id;

    /**
     * El nombre del equipo.
     */
    @NotBlank(message = "El campo nombre es obligatorio.")
    @Schema(description = "El nombre del equipo.", example = "Real Madrid")
    private String nombre;

    /**
     * La liga en la que participa el equipo.
     */
    @NotBlank(message = "El campo liga es obligatoria.")
    @Schema(description = "La liga en la que participa el equipo.", example = "La Liga")
    private String liga;

    /**
     * El país del equipo.
     */
    @NotBlank(message = "El campo país es obligatorio.")
    @Schema(description = "El país del equipo.", example = "España")
    private String pais;

    /**
     * Constructor por defecto para crear una instancia de EquipoEntity.
     */
    public EquipoEntity() {
    }

    /**
     * Constructor para crear una instancia de EquipoEntity con todos los campos.
     *
     * @param id     el identificador único del equipo
     * @param nombre el nombre del equipo
     * @param liga   la liga en la que participa el equipo
     * @param pais   el país del equipo
     */
    public EquipoEntity(Long id, String nombre, String liga, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.liga = liga;
        this.pais = pais;
    }

    /**
     * Obtiene el identificador del equipo.
     *
     * @return retorn el identificador.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del equipo.
     *
     * @param id hace referencia al identificador.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del equipo.
     *
     * @return retorna el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del equipo.
     *
     * @param nombre el nombre del equipo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la liga en la que participa el equipo.
     *
     * @return retorna la liga.
     */
    public String getLiga() {
        return liga;
    }

    /**
     * Establece la liga en la que participa el equipo.
     *
     * @param liga la liga en la que participa el equipo
     */
    public void setLiga(String liga) {
        this.liga = liga;
    }

    /**
     * Obtiene el país del equipo.
     *
     * @return retorna el país.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el país del equipo.
     *
     * @param pais el país del equipo
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
}
