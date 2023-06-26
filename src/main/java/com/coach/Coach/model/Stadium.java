package com.coach.Coach.model;

import jakarta.persistence.*;

@Entity
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stadium_id")
    private Long stadiumId;
    private String stadiumName;
    private int stadiumAge;

    public Long getId() {
        return stadiumId;
    }

    public void setId(Long id) {
        this.stadiumId = id;
    }

    public String getName() {
        return stadiumName;
    }

    public void setName(String name) {
        this.stadiumName = name;
    }

    public int getAge() {
        return stadiumAge;
    }

    public void setAge(int age) {
        this.stadiumAge = age;
    }
}
