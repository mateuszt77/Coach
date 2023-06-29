package com.coach.footballProject.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long playerId;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "fk_coach_id")
    private Coach coach;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "player_stadium",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "stadium_id"))
    public Set<Stadium> stadiums;

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

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public Set<Stadium> getStadiums() {
        return stadiums;
    }

    public void setStadiums(Set<Stadium> stadiums) {
        this.stadiums = stadiums;
    }

    public void addStadium(Stadium stadium) {
        this.stadiums.add(stadium);
    }
}