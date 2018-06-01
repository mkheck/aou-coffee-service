package com.thehecklers.coffeeservice;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

@Service
public class CoffeeService {
    private final CoffeeRepository repo;

    public CoffeeService(CoffeeRepository repo) {
        this.repo = repo;
    }

    public Flux<Coffee> getAllCoffees() {
        return repo.findAll();
    }

    public Mono<Coffee> getCoffeeById(String id) {
        return repo.findById(id);
    }

    public Flux<CoffeeOrder> getOrdersForCoffee(String coffeeId) {
        return Flux.<CoffeeOrder>generate(sink -> sink.next(new CoffeeOrder(coffeeId, Instant.now())))
                .delayElements(Duration.ofSeconds(1));
    }

    public Mono<Coffee> getRandomCoffee() {
        return repo.count()
                .map(Long::intValue)
                .map(new Random()::nextInt)
                .flatMap(repo.findAll()::elementAt);
    }
}
