package com.movies.movieservice.configuration;

import com.movies.movieservice.domain.Movie;
import com.movies.movieservice.domain.MovieEvent;
import com.movies.movieservice.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class WebConfiguration {

    @Bean
    RouterFunction<?> routes(MovieService movieService) {
        return route(GET("/movies"),
                request -> ok().body(movieService.all(), Movie.class))
                .andRoute(GET("/movies/{id}"),
                        request -> ok().body(movieService.byId(request.pathVariable("id")), Movie.class))
                .andRoute(GET("/movies/{id}/events"),
                        request -> ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(movieService.byId(request.pathVariable("id"))
                                        .flatMapMany(movieService::streamStreams), MovieEvent.class));
    }
}
