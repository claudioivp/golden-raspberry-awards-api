package com.texoit.goldenraspberryawardsapi.application.core.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class Producer {

    private UUID id;
    private String name;
    private final Set<Movie> movies;

    public Producer() {
        this.movies = new LinkedHashSet<>();
    }

    public Producer(UUID id, String name, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = new LinkedHashSet<>(movies);
    }

    public Producer(String name) {
        this.name = name;
        this.movies = new LinkedHashSet<>();
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

    public Set<Movie> getMovies() {
        return Collections.unmodifiableSet(movies);
    }

    public void setMovies(Set<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }
}
