package com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity(name = "Producer")
public class ProducerEntity {

    @Id
    @UuidGenerator
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "producers", fetch = FetchType.EAGER)
    private final Set<MovieEntity> movies;

    public ProducerEntity() {
        this.movies = new LinkedHashSet<>();
    }

    public ProducerEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.movies = new LinkedHashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMovies(Set<MovieEntity> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }

    public Set<MovieEntity> getMovies() {
        return Collections.unmodifiableSet(movies);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProducerEntity that = (ProducerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
