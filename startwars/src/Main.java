import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Movie> movies = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while(true){
            System.out.println("Elige alguna de las películas entre a 1 y la 6, de otro modo \n"+
                    "escribe 0 para salir del programa");

            var num_film = scanner.nextInt();

            if(num_film == 0)break;

            try{
                SearchFilm search = new SearchFilm();
                Movie movie = search.searchMovie(num_film);
                movies.add(movie);
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("End aplication");
            }
        }

        FileCreator creator = new FileCreator();
        creator.createjson(movies,gson);
        System.out.println(movies);

    }
}
