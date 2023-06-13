package com.coach.Coach;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Club {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    public Club(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
