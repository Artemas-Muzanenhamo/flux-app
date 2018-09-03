package com.movies.movieservice;

import com.movies.movieservice.domain.Movie;
import com.movies.movieservice.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class MovieServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(MovieRepository movieRepository) {
        return args -> {

            movieRepository.deleteAll()
                    .subscribe(null, null, () -> Stream.of("Aeon Flux", "Enter the Mono<Void>",
                            "The Fluxinator", "Silence of the Lambdas", "Back to the future", "Y Tu Mono Tambien",
                            "Reactive Mongos on a Plane")
                            .map(name -> new Movie(UUID.randomUUID().toString(), name, randomGenre()))
                            .forEach(movie -> movieRepository.save(movie).subscribe(System.out::println))
                    );
        };
    }

    private String randomGenre() {
        String[] genres = "horror, drama, documentary, action, sci-fi".split(",");
        return genres[new Random().nextInt(genres.length)];
    }
}
