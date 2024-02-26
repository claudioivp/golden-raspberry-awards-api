package com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.mapper.MovieMapper;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.request.MovieRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.response.MovieResponse;
import com.texoit.goldenraspberryawardsapi.application.ports.in.movie.FindMovieByIdInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.movie.InsertMovieInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    private final InsertMovieInputPort insertMovieInputPort;
    private final FindMovieByIdInputPort findMovieByIdInputPort;
    private final MovieMapper movieMapper;

    public MovieController(InsertMovieInputPort insertMovieInputPort, MovieMapper movieMapper, FindMovieByIdInputPort findMovieByIdInputPort) {
        this.insertMovieInputPort = insertMovieInputPort;
        this.movieMapper = movieMapper;
        this.findMovieByIdInputPort = findMovieByIdInputPort;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> insert(@Valid @RequestBody MovieRequest movieRequest, UriComponentsBuilder uriBuilder) {
        var movie = insertMovieInputPort.insert(movieMapper.toMovie(movieRequest));
        var uri = uriBuilder.path("api/v1/movies/{id}").buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(uri).body(movieMapper.toMovieResponse(movie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> retrieve(@PathVariable UUID id) {
        var movieResponse = movieMapper.toMovieResponse(findMovieByIdInputPort.find(id));
        return ResponseEntity.ok(movieResponse);
    }

}