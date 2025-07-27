package com.alura.literalura.Servicios;

import com.alura.literalura.DTO.AutorDatos;
import com.alura.literalura.DTO.ConvierteDatos;
import com.alura.literalura.DTO.LibroDatos;
import com.alura.literalura.DTO.ResultadosDatos;
import com.alura.literalura.Entidades.Autor;
import com.alura.literalura.Entidades.Libro;
import com.alura.literalura.Repositorios.AutorRepositorio;
import com.alura.literalura.Repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibroServicio {
    private APIServicio consumoApi = new APIServicio();
    private ConvierteDatos conversorDeDatos = new ConvierteDatos();
    private String url = "https://gutendex.com";
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    LibroRepositorio libroRepositorio;
    @Autowired
    AutorRepositorio autorRepositorio;

    public void getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var tituloLibro = scanner.nextLine();
        System.out.println("Titulo a buscar: " + tituloLibro);
        var json = consumoApi.obtenerDatosAPI(url + "/books?search=" + tituloLibro.replace(" ", "%20"));
        ResultadosDatos resultados = conversorDeDatos.obtenerDatos(json, ResultadosDatos.class);
        System.out.println("Datos recibidos: " + resultados);
        resultados.libros().stream()
                .findFirst()
                .ifPresent(libroDatos -> {
                    Optional<AutorDatos> autorDatosOpt = libroDatos.autor().stream().findFirst();
                    if (autorDatosOpt.isPresent()) {
                        AutorDatos autorDatos = autorDatosOpt.get();
                        Autor autor = new Autor();
                        autor.setNombre(autorDatos.nombre());
                        autor.setFechaNacimiento(autorDatos.fechaNacimiento());
                        autor.setFechaFallecimiento(autorDatos.fechaFallecimiento());

                        Libro libro = new Libro(
                                libroDatos.IdLibro(),
                                libroDatos.titulo(),
                                libroDatos.idiomas(),
                                libroDatos.cantidadDescargas(),
                                autor
                        );

                        autor.setLibros(Collections.singletonList(libro));

                        autorRepositorio.save(autor);
                        libroRepositorio.save(libro);
                    } else {
                        System.out.println("No se encontró un autor para este libro.");
                    }
                });
    }

    public void listarTodosLosLibros() {
        System.out.println("Lista de los libros guardados");
        List<Libro> libros = libroRepositorio.findAll();
        System.out.println(libros);
    }
    public void getLibrosPorIdioma() {
        String idioma = "";
        System.out.println("Idiomas disponibles:");
        System.out.println("1 - Ingles");
        System.out.println("2 - Español");
        System.out.println("3 - Frances");
        System.out.println("4 - Portugues(Brasil)");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                idioma = "en";
                break;
            case 2:
                idioma = "es";
                break;
            case 3:
                idioma = "fr";
                break;
            case 4:
                idioma = "br";
                break;
        }
        List<Libro> librosPorIdioma = libroRepositorio.findByIdiomas(Collections.singletonList(idioma));
        System.out.println("Los libros en el idioma seleccionado son: " + librosPorIdioma);
    }
    public void getCantidadDeLibrosPorIdioma() {
        String idioma = "";
        System.out.println("Idiomas disponibles:");
        System.out.println("1 - Ingles");
        System.out.println("2 - Español");
        System.out.println("3 - Frances");
        System.out.println("4 - Portugues(Brasil)");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                idioma = "en";
                break;
            case 2:
                idioma = "es";
                break;
            case 3:
                idioma = "fr";
                break;
            case 4:
                idioma = "br";
                break;
        }
        List<Libro> librosPorIdioma = libroRepositorio.findByIdiomas(Collections.singletonList(idioma));
        System.out.println("La cantidad de libros en el idioma seleccionado es de : " + librosPorIdioma.size());
    }

    //Metodo inicial que busca en la API para encontrar libros por idioma y los guarda en la base de datos, lo comento para referencias futuras por si necesito algo así.
//    public void getLibrosPorIdioma() {
//        String idioma = "";
//        System.out.println("Idiomas disponibles:");
//        System.out.println("1 - Ingles");
//        System.out.println("2 - Español");
//        System.out.println("3 - Frances");
//        System.out.println("4 - Portugues(Brasil)");
//
//        int opcion = scanner.nextInt();
//        scanner.nextLine();
//        switch (opcion) {
//            case 1:
//                idioma = "en";
//                break;
//            case 2:
//                idioma = "es";
//                break;
//            case 3:
//                idioma = "fr";
//                break;
//            case 4:
//                idioma = "br";
//                break;
//        }
//        var json = consumoApi.obtenerDatosAPI(url + "/books/?languages=" + idioma);
//        System.out.println();
//        ResultadosDatos resultados = conversorDeDatos.obtenerDatos(json, ResultadosDatos.class);
//        System.out.println("Libros Recibidos: " + resultados);
//        resultados.libros().stream()
//                .findFirst()
//                .ifPresent(libroDatos -> {
//                    Optional<AutorDatos> autorDatosOpt = libroDatos.autor().stream().findFirst();
//                    if (autorDatosOpt.isPresent()) {
//                        AutorDatos autorDatos = autorDatosOpt.get();
//                        Autor autor = new Autor();
//                        autor.setNombre(autorDatos.nombre());
//                        autor.setFechaNacimiento(autorDatos.fechaNacimiento());
//                        autor.setFechaFallecimiento(autorDatos.fechaFallecimiento());
//
//                        Libro libro = new Libro(
//                                libroDatos.IdLibro(),
//                                libroDatos.titulo(),
//                                libroDatos.idiomas(),
//                                libroDatos.cantidadDescargas(),
//                                autor
//                        );
//
//                        autor.setLibros(Collections.singletonList(libro));
//
//                        autorRepositorio.save(autor); // Primero guardamos al autor
//                        libroRepositorio.save(libro); // Luego el libro
//                    } else {
//                        System.out.println("❗ No se encontró un autor para este libro.");
//                    }
//                });
}


