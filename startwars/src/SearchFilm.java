import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SearchFilm {

    public Movie searchMovie(int num_movie){

        URI dir =  URI.create("https://swapi.py4e.com/api/films/" + num_movie + "/");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(dir)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Movie.class);
        } catch (Exception e) {
            throw new RuntimeException("No encontre la película");
        }

    }
}
