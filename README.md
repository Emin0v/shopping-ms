# Shopping API Microservice Architecture

This project contains a microservice architecture for a shopping application simulator.

## Understanding this project

This project was developed as a proof of concept for achieve two main goals. The first one is to demonstrate that it is possible to build a microservice architecture using both decentralized and shared databases.

The second goal is to implement a DevOps approach to build a set of Spring Boot microservices inside a docker container and use them by another container.

The third goal is to provide security and logging in the microservice architecture

## Getting started
This is a Spring Boot application built using <a href="https://spring.io/guides/gs/maven/">Maven</a>.

You don't need java or maven in your machine because this project is compiled inside a docker container.

### Prerequisites

* <a href="https://www.oracle.com/java/technologies/downloads/">JDK 11</a>
* <a href="https://www.jetbrains.com/idea/">Intellij Idea Ultimate</a>
* <a href="https://www.gnu.org/software/make/">Make</a>
* <a href="https://git-scm.com/">Git</a>
* <a href="https://www.docker.com/">Docker</a>
* <a href="https://docs.docker.com/compose/gettingstarted/">Docker-compose</a>

## Modules

* config-service
* auth-service
* customer-service
* eureka-service
* cart-service
* zipkin-service
* order-service
* product-service
* gateway-service
* filestore-service
* notification-service

## Databases

* mysql
* elasticsearch
* mongo

### Run

All you need to do is run the following commands:

	git clone https://github.com/Emin0v/shopping-ms
	cd netflix-microservices
	make build
	make run

You will see all registered microservices as the following image:

![](../../Pictures/Screenshots/Screenshot (100).png)