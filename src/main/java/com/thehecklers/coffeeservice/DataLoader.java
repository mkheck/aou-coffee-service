package com.thehecklers.coffeeservice;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {
    private final CoffeeRepository repo;

    public DataLoader(CoffeeRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    private void load() {
        repo.deleteAll().thenMany(
                Flux.just("Kaldi's Coffee", "Espresso Roast", "Pike Place", "Illy Cafe Espresso")
                        .map(name -> new Coffee(name))
                        .flatMap(repo::save))
                .thenMany(repo.findAll())
                .subscribe(System.out::println);
    }
}
