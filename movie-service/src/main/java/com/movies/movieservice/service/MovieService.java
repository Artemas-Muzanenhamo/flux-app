package com.movies.movieservice.service;

import com.movies.movieservice.domain.Movie;
import com.movies.movieservice.domain.MovieEvent;
import com.movies.movieservice.repository.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // endpoint to stream unlimited amount of data related to
    // the amount of movie streamed.
    public Flux<MovieEvent> streamStreams(Movie movie) {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        // Since I don't have real users watching movies, I'll create dummy movie events
        Flux<MovieEvent> events = Flux.fromStream(Stream.generate(
                () -> new MovieEvent(movie, new Date(), randomUser())));

        return Flux.zip(interval, events).map(Tuple2::getT2);
    }

    private String randomUser() {
        String[] users = "Tom, Artemas, Mark, Jimmy, Lebron, Kobe, Jordan".split(",");
        return users[new Random().nextInt(users.length)];
    }

    public Flux<Movie> all(){
        return movieRepository.findAll();
    }

    public Mono<Movie> byId(String id){
        return movieRepository.findById(id);
    }
}
