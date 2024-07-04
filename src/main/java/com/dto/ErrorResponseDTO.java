package com.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase ErrorResponse: Representa una respuesta de error en la API.
 */
@Schema(description = "Representa una respuesta de error en la API.")
public class ErrorResponseDTO {

    /**
     * Mensaje de error en formato string.
     */
    @Schema(description = "Mensaje de error.")
    private String message;

    /**
     * Constructor para crear una instancia de ErrorResponse con un mensaje
     * específico.
     *
     * @param message es el mensaje de error.
     */
    public ErrorResponseDTO(String message) {
        this.message = message;
    }

    /**
     * Obtiene el mensaje de error.
     *
     * @return retorna el mensaje de error.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece el mensaje de error.
     *
     * @param message es el mensaje de error a establecer.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
