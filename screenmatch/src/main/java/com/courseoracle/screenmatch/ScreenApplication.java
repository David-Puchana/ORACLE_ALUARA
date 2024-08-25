package com.courseoracle.screenmatch;
import com.courseoracle.screenmatch.main.ExampleString;
import com.courseoracle.screenmatch.main.Main;
import com.courseoracle.screenmatch.model.DataEpisode;
import com.courseoracle.screenmatch.model.DataSeason;
import com.courseoracle.screenmatch.model.DataSerie;
import com.courseoracle.screenmatch.service.ConvertData;
import com.courseoracle.screenmatch.service.UseAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ScreenApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //var useAPI = new UseAPI();
        //var json = useAPI.getData("https://coffee.alexflipnote.dev/random.json");
        //var json = useAPI.getData("http://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=ebe4454a");
        //System.out.println(json);
        //ConvertData convert = new ConvertData();
        //var data = convert.getData(json, DataEpisode.class);
        //System.out.println(data);
        Main main = new Main();
        main.readMenu();

        //ExampleString exampleString = new ExampleString();
        //exampleString.showExample();
    }
}
