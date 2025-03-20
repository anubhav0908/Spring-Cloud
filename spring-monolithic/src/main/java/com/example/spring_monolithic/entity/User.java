package com.example.spring_monolithic.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  // Ensures 'name' cannot be null
    private String name;

    // Getter Method
    public String getName() { // Ensure this method exists
        return name;
    }
}

