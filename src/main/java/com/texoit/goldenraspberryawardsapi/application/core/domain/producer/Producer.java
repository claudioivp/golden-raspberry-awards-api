package com.texoit.goldenraspberryawardsapi.application.core.domain.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;

import java.util.*;

public class Producer {

    private UUID id;
    private String name;
    private final List<Movie> movies;

    public Producer() {
        this.movies = new ArrayList<>();
    }

    public Producer(UUID id, String name, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = new ArrayList<>(movies);
    }

    public Producer(String name) {
        this.name = name;
        this.movies = new ArrayList<>();
    }

    public Producer(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.movies = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return Collections.unmodifiableList(movies);
    }

    public void setMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
