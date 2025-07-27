package com.alura.literalura.DTO;

import com.alura.literalura.Entidades.Autor;
import com.alura.literalura.Enums.Idiomas;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDatos(
        @JsonAlias("id")Long IdLibro,
        @JsonAlias("title")String titulo,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count")int cantidadDescargas,
        @JsonAlias("authors")
        List<AutorDatos> autor)
{
        @Override
        public String toString() {
                return "IdLibro=" + IdLibro +
                        ", titulo='" + titulo +
                        ", idiomas=" + idiomas +
                        ", cantidadDescargas=" + cantidadDescargas  +
                        ", autor=" + autor + '\n';
        }
        // "id": <number of Project Gutenberg ID>,
    //  "title": <string>,
    //  "subjects": <array of strings>,
    //  "authors": <array of Persons>,
    //  "summaries": <array of strings>,
    //  "translators": <array of Persons>,
    //  "bookshelves": <array of strings>,
    //  "languages": <array of strings>,
    //  "copyright": <boolean or null>,
    //  "media_type": <string>,
    //  "formats": <Format>,
    //  "download_count": <number>
}
