package com.courseoracle.screenmatch.main;

import com.courseoracle.screenmatch.model.DataSeason;
import com.courseoracle.screenmatch.model.DataSerie;
import com.courseoracle.screenmatch.service.ConvertData;
import com.courseoracle.screenmatch.service.UseAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner keyboard = new Scanner(System.in);
    private UseAPI useAPI = new UseAPI();
    private ConvertData convert = new ConvertData();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=ebe4454a";

    public void readMenu(){
        System.out.println("Type the name of the series you want to search for");
        var nameSerie = keyboard.nextLine();
        var json = useAPI.getData(URL_BASE + nameSerie.replace(" ","+") + API_KEY);
        var data = convert.getData(json, DataSerie.class);
        System.out.println(data);

        List<DataSeason> dataSeasons = new ArrayList<>();
        for(int i = 1; i <= data.totalSeasons(); i++){
            json = useAPI.getData(URL_BASE + nameSerie.replace(" ","+") + "&Season=" + i + API_KEY);
            var dataSeason = convert.getData(json, DataSeason.class);
            dataSeasons.add(dataSeason);
        }
        //dataSeasons.forEach(System.out::println);

        dataSeasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));
    }
}
