# Reactive Flux App

## Pre-requisites

* Java 1.8+
* Spring 2+
* Gradle

## Run Movie-Service

* Execute `./gradlew bootRun` inside the `Movie-Service` MicroService.

## Run Movie-Client

* Execute `./gradlew bootRun` inside the `Movie-Client` Microservice.
* After you should see in your terminal every `MovieEvent` that occurs every second 
from our subscription to the `Movie-Service`.
