package com.aluracurse.challengebooks.model;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DataAutor(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") int birthYear,
        @JsonAlias("death_year") int deathYear
) {
}
