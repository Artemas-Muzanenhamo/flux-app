package com.movies.movieservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class MovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner demo() {
		return args -> {
			Flux<String> flux = Flux.fromArray("1,2,3,4,5".split(","));
			flux.map(Integer::parseInt)
					.filter(x -> x % 2 == 0)
					.subscribe(System.out::println, null, null);

			System.out.println("==========");

			final String[] value = {""};
			Mono<String> mono = Mono.just("Hello");
			mono.subscribe(v -> value[0] =v, null, null);
			System.out.println(value[0]);
		};
	}
}
