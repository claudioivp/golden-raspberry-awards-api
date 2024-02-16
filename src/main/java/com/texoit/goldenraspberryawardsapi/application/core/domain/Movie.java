package com.texoit.goldenraspberryawardsapi.application.core.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class Movie {

    private UUID id;
    private Integer year;
    private String title;
    private final Set<Studio> studios;
    private final Set<Producer> producers;
    private Boolean winner;

    public Movie() {
        this.studios = new LinkedHashSet<>();
        this.producers = new LinkedHashSet<>();
    }

    public Movie(UUID id, Integer year, String title, Set<Studio> studios, Set<Producer> producers, Boolean winner) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.studios = new LinkedHashSet<>(studios);
        this.producers = new LinkedHashSet<>(producers);
        this.winner = winner;
    }

    public Movie(Integer year, String title, Set<Studio> studios, Set<Producer> producers, Boolean winner) {
        this.year = year;
        this.title = title;
        this.studios = new LinkedHashSet<>(studios);
        this.producers = new LinkedHashSet<>(producers);
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

    public Set<Studio> getStudios() {
        return Collections.unmodifiableSet(studios);
    }

    public void setStudios(Set<Studio> studios) {
        this.studios.clear();
        this.studios.addAll(studios);
    }

    public Set<Producer> getProducers() {
        return Collections.unmodifiableSet(producers);
    }

    public void setProducers(Set<Producer> producers) {
        this.producers.clear();
        this.producers.addAll(producers);
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }
}
