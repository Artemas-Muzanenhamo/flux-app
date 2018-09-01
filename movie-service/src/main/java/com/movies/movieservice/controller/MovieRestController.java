package com.movies.movieservice.controller;

import com.movies.movieservice.domain.Movie;
import com.movies.movieservice.domain.MovieEvent;
import com.movies.movieservice.service.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieRestController {
    public final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvent> events(@PathVariable String id) {
        return movieService.byId(id)
                .flatMapMany(movieService::streamStreams);
    }

    @GetMapping
    public Flux<Movie> movies() {
        return movieService.all();
    }

    @GetMapping("/{id}")
    public Mono<Movie> byId(@PathVariable String id) {
        return movieService.byId(id);
    }


}
