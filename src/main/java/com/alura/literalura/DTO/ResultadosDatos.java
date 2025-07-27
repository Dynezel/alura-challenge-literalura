package com.alura.literalura.DTO;

import com.alura.literalura.Entidades.Libro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadosDatos(
        @JsonAlias("results")
        List<LibroDatos> libros
) {
}
