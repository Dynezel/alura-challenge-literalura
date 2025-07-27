package com.alura.literalura.Servicios;

import com.alura.literalura.Servicios.AutorServicio;
import com.alura.literalura.Servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner teclado = new Scanner(System.in);
    private LibroServicio libroServicio;
    private AutorServicio autorServicio;
    public MenuPrincipal(LibroServicio libroServicio, AutorServicio autorServicio) {
        this.libroServicio = libroServicio;
        this.autorServicio = autorServicio;
    }
        public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar Libros Por Titulo
                    2 - Listar Libros Registrados
                    3 - Listar Autores Registrados
                    4 - Listar Autores vivos en un determinado año
                    5 - Listar Libros por idioma
                    6 - Mostrar cantidad de Libros por un idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    libroServicio.getDatosLibro();
                    break;
                case 2:
                    libroServicio.listarTodosLosLibros();
                    break;
                case 3:
                    autorServicio.listarTodosLosAutores();
                    break;
                case 4:
                    autorServicio.getAutoresVivosEnAnio();
                    break;
                case 5:
                    libroServicio.getLibrosPorIdioma();
                    break;
                case 6:
                    libroServicio.getCantidadDeLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
