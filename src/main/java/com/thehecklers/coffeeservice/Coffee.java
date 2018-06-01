package com.thehecklers.coffeeservice;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Coffee {
    @Id
    private String id;
    @NonNull
    private String name;
}
