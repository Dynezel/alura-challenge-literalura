package com.alura.literalura.Entidades;

import com.alura.literalura.DTO.AutorDatos;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "libro")
    public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long idLibro;
    private String titulo;
    private List<String>idiomas;
    private int cantidadDescargas;
    @ManyToOne()
    private Autor autor;

    public Libro(Long idLibro, String titulo, List<String> idiomas, int cantidadDescargas, Autor autor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.cantidadDescargas = cantidadDescargas;
        this.autor = autor;
                //Me equivoqué, hice el desafio sin leer mucho el trello y lo habia implementado como lista, lo comento porque esto funcionaba
//                autoresDatos.stream()
//                .map(autorDatos -> {
//                    Autor autor = new Autor(autorDatos.nombre(), autorDatos.fechaNacimiento(), autorDatos.fechaFallecimiento());
//                    autor.getLibros().add(this);
//                    return autor;
//                })
//                .collect(Collectors.toList());
    }
    public Libro(){
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public void setCantidadDescargas(int cantidadDescargas) {
        this.cantidadDescargas = cantidadDescargas;
    }

    public Autor getAutores() {
        return autor;
    }

    public void setAutores(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return
                "idLibro=" + idLibro + '\n' +
                ", titulo='" + titulo + '\n' +
                ", idiomas=" + idiomas + '\n' +
                ", cantidadDescargas=" + cantidadDescargas + '\n';
    }

    //Tipos de datos de los Libros en la API
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
