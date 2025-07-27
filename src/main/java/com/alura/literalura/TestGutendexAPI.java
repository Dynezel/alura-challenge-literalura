import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestGutendexAPI {
    public static void main(String[] args) {
        String url = "https://gutendex.com/books?search=romeo";

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // ← esto es clave
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Código de respuesta HTTP: " + response.statusCode());

            String json = response.body();

            if (response.statusCode() == 200 && json != null && !json.isBlank()) {
                System.out.println("✅ Respuesta JSON recibida:");
                System.out.println(json);
            } else {
                System.out.println("⚠️ La API no devolvió contenido válido.");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Error al conectar con la API:");
            e.printStackTrace();
        }
    }
}
