package com.alura.screenmatch.principal;

import com.alura.screenmatch.exception.ErrorinDurationConversionException;
import com.alura.screenmatch.modelos.TitleOmdb;
import com.alura.screenmatch.modelos.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);

        List<Titulo> titles = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while(true){
            System.out.println("Escriba el nombre de una pelicula: ");
            var busqueda = lectura.nextLine();

            if(busqueda.equalsIgnoreCase("salir")) break;

            String direccion = "https://www.omdbapi.com/?t="+busqueda+"&apikey=d4d0bf92";
            try{
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                System.out.println(json);

                //Titulo myTitle = gson.fromJson(json, Titulo.class);
                TitleOmdb myTitleOmdb = gson.fromJson(json, TitleOmdb.class);
                System.out.println(myTitleOmdb);

                Titulo myTitle = new Titulo(myTitleOmdb);
                System.out.println(myTitle);

                titles.add(myTitle);

            }catch(NumberFormatException e){
                System.out.println("Ocurrio el error: ");
                System.out.println(e.getMessage());
            }catch (ErrorinDurationConversionException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println(titles);

        FileWriter writeTitles = new FileWriter("titles.json");
        writeTitles.write(gson.toJson(titles));
        writeTitles.close();

        System.out.println("end code");

    }
}
