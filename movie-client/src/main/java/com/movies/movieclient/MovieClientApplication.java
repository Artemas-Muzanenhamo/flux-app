package com.movies.movieclient;

import com.movies.movieclient.domain.Movie;
import com.movies.movieclient.domain.MovieEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieClientApplication {

    @Bean
    WebClient webClient() {
        return WebClient.create();
    }

    @Bean
    CommandLineRunner demo(WebClient webClient) {
        return args -> {
            webClient.get()
                    .uri("http://localhost:8080/movies")
                    .exchange()
                    .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Movie.class))
                    .filter(movie -> movie.getTitle().toLowerCase().contains("Aeon Flux".toLowerCase()))
                    .subscribe(movie ->
                            webClient.get()
                                    .uri("http://localhost:8080/movies/{id}/events", movie.getId())
                                    .exchange()
                                    .flatMapMany(clientResponse -> clientResponse.bodyToFlux(MovieEvent.class))
                                    .subscribe(System.out::println));

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieClientApplication.class, args);
    }
}
