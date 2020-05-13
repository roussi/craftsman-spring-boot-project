# craftsman-spring-boot-project ( v0.1.0-beta )
## Introduction

The purpose of this project is to contain (as much as possible) craftsmanship implementation of various concept around the usage of spring boot.
It will contain integration of several end-to-end stack frameworks with spring boot application.

I'll try to introduce many cloud native concepts in this project as I can, so please feel free to contribute if you can.

The final release will be a dockerized application that can be deployed to Kubernetes.

**Technical-stack will be:**
* Core framework: Spring boot2
* Database: Postgres DB + Flyway integration (maybe R2DBC)
* Tracing & Monitoring : Spring Cloud Sleuth + ELK stack
* Cache support: Redis (maybe Hazelcast)
* Config & Security: K8S Secrets + OAuth2 + Vault
* Test : Junit5, Mockito, Karate, TestContainer, Selenium
* Fault tolerance: resilience4j
* Mock : mockit, karate mocks