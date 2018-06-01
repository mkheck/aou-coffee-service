package com.thehecklers.coffeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@EnableDiscoveryClient
@SpringBootApplication
public class CoffeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeServiceApplication.class, args);
    }
}
