package com.alura.literalura.Repositorios;

import com.alura.literalura.Entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(int anio, int anio2);
}
