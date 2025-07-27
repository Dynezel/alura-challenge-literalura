package com.alura.literalura.Servicios;

import com.alura.literalura.DTO.IConvierteDatos;
import com.alura.literalura.Entidades.Autor;
import com.alura.literalura.Entidades.Libro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class APIServicio {
    public String obtenerDatosAPI(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Sin esta linea no funciona
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Código de respuesta HTTP: " + response.statusCode());

            if (response.statusCode() != 200) {
                System.out.println("Error al hacer la solicitud HTTP. Código: " + response.statusCode());
                return "";
            }

            return response.body();

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al conectarse a la API: " + e.getMessage());
            return "";
        }
    }
}
