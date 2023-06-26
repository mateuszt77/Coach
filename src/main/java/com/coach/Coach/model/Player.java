package com.coach.Coach.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long playerId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "fk_coach_id")
    private Coach coach;

    private String playerName;
    private int playerAge;


    public Long getId() {
        return playerId;
    }

    public void setId(Long id) {
        this.playerId = id;
    }

    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        this.playerName = name;
    }

    public int getAge() {
        return playerAge;
    }

    public void setAge(int age) {
        this.playerAge = age;
    }

    public List<Coach> getCoaches() {
        return getCoaches();
    }
    public void setGreenhouses(List<Coach> coaches) {
        this.coach = coach;
    }
}
