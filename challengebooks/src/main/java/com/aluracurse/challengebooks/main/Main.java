package com.aluracurse.challengebooks.main;
import com.aluracurse.challengebooks.model.DataBooks;
import com.aluracurse.challengebooks.service.ConverterData;
import com.aluracurse.challengebooks.service.RequestAPI;
import com.aluracurse.challengebooks.model.Data;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final String URI_BASE = "https://gutendex.com/books/";

    private RequestAPI requestAPI = new RequestAPI();
    private ConverterData converter = new ConverterData();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu(){
        var json = requestAPI.getData(URI_BASE);
        var data = converter.getData(json, Data.class);
        System.out.println(data);

        //top 10 downloads
        System.out.println("Top 10 downloads");
        data.boocks().stream()
                .sorted(Comparator.comparing(DataBooks::downloadCount).reversed())
                .limit(10)
                .map(l -> l.title().toUpperCase())
                .forEach(System.out::println);

        //Search for books by name
        System.out.println("Write the book name for search");
        var search = scanner.nextLine();
        String url_search = URI_BASE + "?search="+search.replace(" ", "+");
        json = requestAPI.getData(url_search);
        var resultSearch = converter.getData(json, Data.class);
        Optional<DataBooks> bookSearch = resultSearch.boocks().stream()
                .filter(l -> l.title().toUpperCase().contains(search.toUpperCase()))
                .findFirst();

        if(bookSearch.isPresent()){
            System.out.println("This is the Book");
            System.out.println(bookSearch.get());
        } else {
            System.out.println("The book was not found");
        }

        IntSummaryStatistics statistics = data.boocks().stream()
                .filter(d -> d.downloadCount() > 0)
                .collect(Collectors.summarizingInt(DataBooks::downloadCount));

        System.out.println("Average downloads: " + statistics.getAverage());

        data.boocks().stream()
                .max(Comparator.comparing(DataBooks::downloadCount))
                .map(l -> l.title().toUpperCase())
                .ifPresent(System.out::println);
        System.out.println("Max downloads: " + statistics.getMax());
        data.boocks().stream()
                .min(Comparator.comparing(DataBooks::downloadCount))
                .map(l -> l.title().toUpperCase())
                .ifPresent(System.out::println);
        System.out.println("Min downloads: " + statistics.getMin());
        System.out.println("Count data: " + statistics.getCount());

    }
}
