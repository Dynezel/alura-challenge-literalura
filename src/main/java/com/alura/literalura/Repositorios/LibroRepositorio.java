package com.alura.literalura.Repositorios;

import com.alura.literalura.Entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    Libro findByTitulo(String titulo);

    List<Libro> findByIdiomas(List<String> idioma);

    boolean existsByTitulo(String titulo);
}
