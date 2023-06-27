package com.coach.Coach.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stadiumId;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinTable(name = "player_stadium",
            joinColumns = @JoinColumn(name = "stadium_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> players;
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

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }
}