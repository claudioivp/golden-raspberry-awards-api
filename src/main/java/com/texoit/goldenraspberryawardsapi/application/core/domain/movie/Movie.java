package com.texoit.goldenraspberryawardsapi.application.core.domain.movie;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;

import java.util.*;

public class Movie {

    private UUID id;
    private Integer year;
    private String title;
    private final List<Studio> studios;
    private final List<Producer> producers;
    private Boolean winner;

    public Movie() {
        this.studios = new ArrayList<>();
        this.producers = new ArrayList<>();
    }

    public Movie(UUID id, Integer year, String title, List<Studio> studios, List<Producer> producers, Boolean winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.studios = new ArrayList<>(studios);
        this.producers = new ArrayList<>(producers);
        this.winner = winner;
    }

    public Movie(Integer year, String title, List<Studio> studios, List<Producer> producers, Boolean winner) {
        this.year = year;
        this.title = title;
        this.studios = new ArrayList<>(studios);
        this.producers = new ArrayList<>(producers);
        this.winner = winner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Studio> getStudios() {
        return Collections.unmodifiableList(studios);
    }

    public void setStudios(List<Studio> studios) {
        this.studios.clear();
        this.studios.addAll(studios);
    }

    public List<Producer> getProducers() {
        return Collections.unmodifiableList(producers);
    }

    public void setProducers(List<Producer> producers) {
        this.producers.clear();
        this.producers.addAll(producers);
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", studios=" + studios +
                ", producers=" + producers +
                ", winner=" + winner +
                '}';
    }
}
