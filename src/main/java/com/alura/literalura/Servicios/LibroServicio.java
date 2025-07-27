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

    public void buscarYGuardarLibrosPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        String tituloLibro = scanner.nextLine();
        String json = consumoApi.obtenerDatosAPI(url + "/books?search=" + tituloLibro.replace(" ", "%20"));
        ResultadosDatos resultados = conversorDeDatos.obtenerDatos(json, ResultadosDatos.class);

        resultados.libros().stream().findFirst().ifPresentOrElse(libro -> {
            mostrarInformacionLibro(libro);
            System.out.println("¿Deseas guardar este libro en la base de datos? (s/n)");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("s")) {
                guardarLibroEnBD(libro);
            } else {
                System.out.println("ℹ️ El libro no fue guardado.");
            }
        }, () -> System.out.println("❌ No se encontró ningún libro con ese nombre."));
    }

    private void mostrarInformacionLibro(LibroDatos libro) {
        System.out.printf("📖 Título: %s%n", libro.titulo());
        System.out.printf("🌐 Idiomas: %s%n", String.join(", ", libro.idiomas()));
        System.out.printf("⬇️ Descargas: %d%n", libro.cantidadDescargas());

        libro.autor().stream().findFirst().ifPresentOrElse(autor -> {
            System.out.printf("👤 Autor: %s%n", autor.nombre());
            if (autor.fechaNacimiento() != null)
                System.out.printf("🎂 Nacimiento: %s%n", autor.fechaNacimiento());
            if (autor.fechaFallecimiento() != null)
                System.out.printf("⚰️ Fallecimiento: %s%n", autor.fechaFallecimiento());
        }, () -> System.out.println("⚠️ No se encontró autor para este libro."));
    }

    private void guardarLibroEnBD(LibroDatos libroDatos) {
        if (libroRepositorio.existsById(libroDatos.IdLibro())) {
            System.out.println("⚠️ Ya existe un libro con esa ID en la base de datos.");
            return;
        }

        if (libroRepositorio.existsByTitulo(libroDatos.titulo())) {
            System.out.println("⚠️ Ya existe un libro con ese titulo en la base de datos.");
            return;
        }

        Optional<AutorDatos> autorDatosOpt = libroDatos.autor().stream().findFirst();
        if (autorDatosOpt.isEmpty()) {
            System.out.println("❗ No se encontró un autor para este libro.");
            return;
        }

        AutorDatos autorDatos = autorDatosOpt.get();
        Autor autor = autorRepositorio.findByNombre(autorDatos.nombre()).orElseGet(() -> {
            Autor nuevoAutor = new Autor();
            nuevoAutor.setNombre(autorDatos.nombre());
            nuevoAutor.setFechaNacimiento(autorDatos.fechaNacimiento());
            nuevoAutor.setFechaFallecimiento(autorDatos.fechaFallecimiento());
            return nuevoAutor;
        });

        Libro libro = new Libro(
                libroDatos.IdLibro(),
                libroDatos.titulo(),
                libroDatos.idiomas(),
                libroDatos.cantidadDescargas(),
                autor
        );

        if (autor.getLibros() == null) autor.setLibros(new ArrayList<>());
        autor.getLibros().add(libro);

        autorRepositorio.save(autor);
        libroRepositorio.save(libro);

        System.out.printf("✅ Libro '%s' Escrito por %s ,fue guardado correctamente.%n",
                libro.getTitulo(), autor.getNombre());
    }

    public void listarTodosLosLibros() {
        System.out.println("Lista de los libros guardados");
        List<Libro> libros = libroRepositorio.findAll();
        System.out.println(libros);
    }

    public void getLibrosPorIdioma() {
        String idioma = seleccionarIdioma();
        List<Libro> librosPorIdioma = libroRepositorio.findByIdiomas(Collections.singletonList(idioma));
        System.out.println("Los libros en el idioma seleccionado son: " + librosPorIdioma);
    }

    public void getCantidadDeLibrosPorIdioma() {
        String idioma = seleccionarIdioma();
        long cantidad = libroRepositorio.findByIdiomas(Collections.singletonList(idioma)).size();
        System.out.println("🔢 Cantidad de libros en " + idioma + ": " + cantidad);
    }

    private String seleccionarIdioma() {
        Map<String, String> idiomas = new LinkedHashMap<>();
        idiomas.put("en", "Inglés");
        idiomas.put("es", "Español");
        idiomas.put("fr", "Francés");
        idiomas.put("br", "Portugués Brasileño");

        System.out.println("Idiomas disponibles:");

        List<String> claves = new ArrayList<>(idiomas.keySet());

        for (int i = 0; i < claves.size(); i++) {
            String clave = claves.get(i);
            System.out.println((i + 1) + " - " + idiomas.get(clave) + " (" + clave + ")");
        }

        int opcion;
        try {
            opcion = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Se usará inglés por defecto.");
            return "en";
        }

        if (opcion >= 1 && opcion <= claves.size()) {
            return claves.get(opcion - 1);
        } else {
            System.out.println("Opción inválida. Se usará inglés por defecto.");
            return "en";
        }
    }
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



