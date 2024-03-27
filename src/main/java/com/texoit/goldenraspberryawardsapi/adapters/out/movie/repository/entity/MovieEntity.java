package com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.entity;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.entity.StudioEntity;
import jakarta.persistence.*;

import java.util.*;

@Entity(name = "Movie")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "productionYear", "title" }) })
public class MovieEntity {

    @Id
    private UUID id;
    @Column(name = "productionYear", nullable = false)
    private Integer year;
    @Column(nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_studio",
            joinColumns = @JoinColumn(name = "movie_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "studio_id", nullable = false)
    )
    private final List<StudioEntity> studios;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_producer",
            joinColumns = @JoinColumn(name = "movie_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "producer_id", nullable = false)
    )
    private final List<ProducerEntity> producers;

    private Boolean winner;

    public MovieEntity() {
        this.studios = new ArrayList<>();
        this.producers = new ArrayList<>();
    }

    public MovieEntity(UUID id, Integer year, String title, List<StudioEntity> studios, List<ProducerEntity> producers, Boolean winner) {
        this.id = id;
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

    public List<StudioEntity> getStudios() {
        return Collections.unmodifiableList(studios);
    }

    public void setStudios(List<StudioEntity> studios) {
        this.studios.clear();
        this.studios.addAll(studios);
    }

    public List<ProducerEntity> getProducers() { return Collections.unmodifiableList(producers); }

    public void setProducers(List<ProducerEntity> producers) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(year, that.year) && Objects.equals(title, that.title) && Objects.equals(studios, that.studios) && Objects.equals(producers, that.producers) && Objects.equals(winner, that.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, title, studios, producers, winner);
    }

    @PrePersist
    protected void onCreate() {
        if (Objects.isNull(this.id)) {
            this.id = UUID.randomUUID();
        }
    }
}
