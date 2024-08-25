package com.courseoracle.screenmatch.main;

import com.courseoracle.screenmatch.model.DataEpisode;
import com.courseoracle.screenmatch.model.DataSeason;
import com.courseoracle.screenmatch.model.DataSerie;
import com.courseoracle.screenmatch.model.Episode;
import com.courseoracle.screenmatch.service.ConvertData;
import com.courseoracle.screenmatch.service.UseAPI;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.net.Socket;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner keyboard = new Scanner(System.in);
    private UseAPI useAPI = new UseAPI();
    private ConvertData convert = new ConvertData();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=ebe4454a";

    public void readMenu(){
        System.out.println("Type the name of the series you want to search for");
        var nameSerie = keyboard.nextLine().replace(" ","+");
        var json = useAPI.getData(URL_BASE + nameSerie + API_KEY);
        var data = convert.getData(json, DataSerie.class);
        System.out.println(data);

        List<DataSeason> dataSeasons = new ArrayList<>();
        for(int i = 1; i <= data.totalSeasons(); i++){
            json = useAPI.getData(URL_BASE + nameSerie + "&Season=" + i + API_KEY);
            var dataSeason = convert.getData(json, DataSeason.class);
            dataSeasons.add(dataSeason);
        }
        //dataSeasons.forEach(System.out::println);
        //dataSeasons.forEach(t -> t.episodes().forEach(e -> System.out.println(e.title())));

        List<DataEpisode> dataEpisodes = dataSeasons.stream()
                        .flatMap(t -> t.episodes().stream())
                        .collect(Collectors.toList());

//        System.out.println("Top five Episodes");

//        dataEpisodes.stream()
//                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
//                .sorted(Comparator.comparing(DataEpisode::rating).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        List<Episode> episodesClass = dataSeasons.stream()
                .flatMap(t -> t.episodes().stream()
                .map(d -> new Episode(t.number(), d)))
                .collect(Collectors.toList());

//        episodesClass.forEach(System.out::println);

//        System.out.println("Enter the year from which you want to see the episodes");
//        var date = keyboard.nextInt();
//        keyboard.nextLine();
//
//        LocalDate dateSearch = LocalDate.of(date, 1,1);
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodesClass.stream()
//                .filter(e -> (e.getReleased() != null) && e.getReleased().isAfter(dateSearch))
//                .forEach(e -> System.out.println("Season: " + e.getSeason() +
//                                                 " Episode: " + e.getTitle() +
//                                                 " Date: " + e.getReleased().format(dtf)));
//
//        System.out.println("Enter the title of the episode");
//        var partTitle = keyboard.nextLine().toUpperCase();
//
//        Optional<Episode> episodeSearch = episodesClass.stream()
//                .filter(e -> e.getTitle().toUpperCase().contains(partTitle))
//                .findFirst();
//
//        if(episodeSearch.isPresent()){
//            System.out.println(episodeSearch.get());
//        } else {
//            System.out.println("No episode title found containing that word");
//        }

        Map<Integer, Double> reatingsBySeason = episodesClass.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason,
                         Collectors.averagingDouble(Episode::getRating)));

        System.out.println(reatingsBySeason);

        DoubleSummaryStatistics statistics = episodesClass.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble((Episode::getRating)));

        System.out.println("Mean: " + statistics.getAverage() + "\n" +
                           "Max: " + statistics.getMax() + "\n" +
                           "Min: " + statistics.getMin());

    }
}
