package com.alura.literalura.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDatos(
        @JsonAlias("name")String nombre,
        @JsonAlias("birth_year")Integer fechaNacimiento,
        @JsonAlias("death_year")Integer fechaFallecimiento) {
    //"birth_year": <number or null>,
    //  "death_year": <number or null>,
    //  "name": <string>
}
