package com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.entity;

import com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.entity.MovieEntity;
import jakarta.persistence.*;

import java.util.*;

@Entity(name = "Producer")
public class ProducerEntity {

    @Id
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "producers", fetch = FetchType.EAGER)
    private final List<MovieEntity> movies;

    public ProducerEntity() {
        this.movies = new ArrayList<>();
    }

    public ProducerEntity(UUID id, String name) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
    }

    public List<MovieEntity> getMovies() {
        return Collections.unmodifiableList(movies);
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

    @PrePersist
    protected void onCreate() {
        if (Objects.isNull(this.id)) {
            this.id = UUID.randomUUID();
        }
    }
}
