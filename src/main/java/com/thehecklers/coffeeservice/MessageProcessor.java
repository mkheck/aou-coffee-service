package com.thehecklers.coffeeservice;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class MessageProcessor {
    private final CoffeeRepository repo;

    public MessageProcessor(CoffeeRepository repo) {
        this.repo = repo;
    }

    @StreamListener(Sink.INPUT)
    public void saveCoffee(Coffee coffee) {
        repo.save(coffee)
                .subscribe(System.out::println);
    }
}
