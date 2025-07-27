package com.alura.literalura.Servicios;

import com.alura.literalura.Entidades.Autor;
import com.alura.literalura.Entidades.Libro;
import com.alura.literalura.Repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio autorRepositorio;
    private APIServicio consumoApi = new APIServicio();
    private String url = "https://gutendex.com/";
    private Scanner scanner = new Scanner(System.in);

    public void listarTodosLosAutores() {
        System.out.println("Estos son todos los autores registrados");
        System.out.println(autorRepositorio.findAll());
    }
    public void getAutoresVivosEnAnio() {
        System.out.println("--- Búsqueda de Autores vivos en un año específico ---");

        int anio;
        while (true) {
            try {
                System.out.print("Ingrese el año: ");
                anio = Integer.parseInt(scanner.nextLine());
                if (anio < -1105 || anio > Year.now().getValue()) {
                    System.out.println("Año inválido. Intente nuevamente (válido entre -1105 y el actual).");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
            }
        }

        List<Autor> autores = (anio < 0)
                ? autorRepositorio.findAutoresVivosEnAnio(anio)
                : autorRepositorio.findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(anio, anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autores.forEach(autor -> System.out.println("Autor: " + autor));
        }
    }
}

    //Metodo para retornar autores vivos segun el año desde la API
//    public void getAutoresPorAnio() {
//        System.out.println("---Busqueda de Autores por año en el que estuvieron vivos---");
//        System.out.println("##Se buscará a los autores que estuvieron vivos entre los siguientes años ingresados##");
//        System.out.println("Ingrese el año inicial del rango de busqueda");
//        int inicio = scanner.nextInt();
//        scanner.nextLine();
//        System.out.println("Ingrese el año final del rango de busqueda");
//        int fin = scanner.nextInt();
//        scanner.nextLine();
//        var json = consumoApi.obtenerDatosAPI(url + "/books?author_year_start=" + inicio + "&author_year_end=" + fin);
//        System.out.println(json);
//
//    }
    //author_year_start and author_year_end
    //Use these to find books with at least one author alive in a given range of years.
    // They must have positive or negative integer values. For example,
    // /books?author_year_end=-499 gives books with authors alive before 500 BCE,
    // and /books?author_year_start=1800&author_year_end=1899 gives books with authors alive in the 19th Century.

