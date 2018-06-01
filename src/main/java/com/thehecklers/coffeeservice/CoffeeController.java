package com.thehecklers.coffeeservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private final CoffeeService service;

    public CoffeeController(CoffeeService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Coffee> getAllCoffees() {
        return service.getAllCoffees();
    }

    @GetMapping("/{id}")
    public Mono<Coffee> getCoffeeById(@PathVariable String id) {
        return service.getCoffeeById(id);
    }

    @GetMapping(value = "/{id}/orders", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CoffeeOrder> getOrdersForCoffee(@PathVariable String id) {
        return service.getOrdersForCoffee(id);
    }

    @GetMapping("/random")
    public Mono<Coffee> getRandomCoffee() {
        return service.getRandomCoffee();
    }
}
