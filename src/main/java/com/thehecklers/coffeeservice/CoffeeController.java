package com.thehecklers.coffeeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private final CoffeeRepository repo;

    public CoffeeController(CoffeeRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Flux<Coffee> getAllCoffees() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Coffee> getCoffeeById(@PathVariable String id) {
        return repo.findById(id);
    }

    @GetMapping("/random")
    public Mono<Coffee> getRandomCoffee() {
        return repo.count()
                .map(Long::intValue)
                .map(new Random()::nextInt)
                .flatMap(repo.findAll()::elementAt);
    }
}
