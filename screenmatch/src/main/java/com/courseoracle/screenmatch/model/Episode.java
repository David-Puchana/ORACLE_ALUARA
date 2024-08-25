package com.courseoracle.screenmatch.model;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Episode {
    private Integer season;
    private String title;
    private Integer numberEpisode;
    private Double rating;
    private LocalDate released;

    public Episode(Integer number, DataEpisode d) {
        this.season = number;
        this.title = d.title();
        this.numberEpisode = d.episode();
        try {
            this.rating = Double.valueOf(d.rating());
        }catch (NumberFormatException e){
            this.rating = 0.0;
        }
        try {
            this.released = LocalDate.parse(d.released());
        }catch (DateTimeException e){
            this.released = null;
        }


    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(Integer numberEpisode) {
        this.numberEpisode = numberEpisode;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    @Override
    public String toString() {
        return "season=" + season +
                ", title='" + title + '\'' +
                ", numberEpisode=" + numberEpisode +
                ", rating=" + rating +
                ", released=" + released;
    }
}
