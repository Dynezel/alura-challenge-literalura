package com.alura.literalura.Entidades;
import com.alura.literalura.DTO.AutorDatos;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros = new ArrayList<>();

    public Autor(Optional<AutorDatos> autorDatos) {
        this.nombre = autorDatos.get().nombre();
        this.fechaNacimiento = autorDatos.get().fechaNacimiento();
        this.fechaFallecimiento = autorDatos.get().fechaFallecimiento();
    }
    public Autor(String nombre, int fechaNacimiento, int fechaFallecimiento, List<Libro> libros) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.libros = libros;
    }
    public Autor(){}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(int fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaFallecimiento=" + fechaFallecimiento + '\n';
    }

    //"birth_year": <number or null>,
    //  "death_year": <number or null>,
    //  "name": <string>


}
