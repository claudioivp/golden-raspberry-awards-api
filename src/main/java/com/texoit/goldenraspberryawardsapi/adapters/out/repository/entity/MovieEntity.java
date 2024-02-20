package com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Movie")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "year", "title" }) })
public class MovieEntity {

    @Id
    @UuidGenerator
    private UUID id;
    private Integer year;
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_producer",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id")
    )
    private Set<ProducerEntity> producers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_studio",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "studio_id")
    )
    private Set<StudioEntity> studios;

    private Boolean winner;

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

    public Set<ProducerEntity> getProducers() {
        return producers;
    }

    public void setProducers(Set<ProducerEntity> producers) {
        this.producers = producers;
    }

    public Set<StudioEntity> getStudios() {
        return studios;
    }

    public void setStudios(Set<StudioEntity> studios) {
        this.studios = studios;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(year, that.year) && Objects.equals(title, that.title) && Objects.equals(producers, that.producers) && Objects.equals(studios, that.studios) && Objects.equals(winner, that.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, title, producers, studios, winner);
    }
}
