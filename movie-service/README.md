# Movie Service

This is a simple application to demonstrate some Reactive Spring
functionality in Spring 5.
The idea here is that given a set of Movies, if you wanted to send data(_events_)
of users that are streaming movies every second, you can achieve this through
Reactive Spring.

---

## Pre-Requisites
* Java 8+
* MongoDB instance running
* Maven
---

## Run the app

- `mvn spring-boot:run`

- `curl http://localhost/movies` <- Returns all movies in a `Flux<Movie>`.
- `curl http://localhost/movies/{id}` <-  Returns a single movie in a `Mono<Movie`.
- `curl http://localhost/movies/{id}/events` <- emits dummy user streaming events every 1 second.