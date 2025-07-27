package com.alura.literalura;

import com.alura.literalura.Servicios.AutorServicio;
import com.alura.literalura.Servicios.LibroServicio;
import com.alura.literalura.Servicios.MenuPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Principal;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private LibroServicio libroServicio;
	@Autowired
	private AutorServicio autorServicio;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MenuPrincipal principal = new MenuPrincipal(libroServicio, autorServicio);
		principal.muestraElMenu();
	}
}
