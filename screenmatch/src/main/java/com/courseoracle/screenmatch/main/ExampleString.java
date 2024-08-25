package com.courseoracle.screenmatch.main;

import java.util.Arrays;
import java.util.List;

public class ExampleString {
    public void showExample(){
        List<String> names = Arrays.asList("Brenda", "Luis", "Maria Fernanda", "Erick", "Genesys");
        names.stream().sorted().limit(2).
        forEach(System.out::println);
    }
}
