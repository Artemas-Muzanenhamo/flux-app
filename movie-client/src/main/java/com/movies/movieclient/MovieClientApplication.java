package com.movies.movieclient;

import com.movies.movieclient.domain.Movie;
import com.movies.movieclient.domain.MovieEvent;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieClientApplication {
    Logger logger = LoggerFactory.getLogger(MovieClientApplication.class);

    @Bean
    WebClient webClient() {
        return WebClient.create();
    }

    @Bean
    CommandLineRunner demo(WebClient webClient) {
        return args -> webClient.get()
                .uri("http://localhost:8082/movies")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Movie.class))
                .filter(movie -> movie.getTitle().toLowerCase().contains("Aeon Flux".toLowerCase()))
                .subscribe(movie ->
                        webClient.get()
                                .uri("http://localhost:8082/movies/{id}/events", movie.getId())
                                .accept(MediaType.TEXT_EVENT_STREAM)
                                .exchange()
                                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(MovieEvent.class))
                                .subscribe(movieEvent -> {
                                    System.out.println(movieEvent.toString());
                                    logger.info(movieEvent.toString());
                                }));
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieClientApplication.class, args);
    }
}
