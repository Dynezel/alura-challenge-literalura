package com.alura.literalura.Repositorios;

import com.alura.literalura.Entidades.Autor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    @EntityGraph(attributePaths = "libros")
    Optional<Autor> findByNombre(String nombre);
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(int anio, int anio2);
    //Query para casos mas especificos en los que la API contiene fechas nulas y la Derived Query no lo toma correctamente
    @Query("SELECT a FROM Autor a WHERE (a.fechaNacimiento IS NULL OR a.fechaNacimiento <= :anio) AND (a.fechaFallecimiento IS NULL OR a.fechaFallecimiento >= :anio)")
    List<Autor> findAutoresVivosEnAnio(@Param("anio") int anio);
}
