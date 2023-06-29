package com.coach.footballProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_id")
    private Long coachId;
    @OneToOne(mappedBy = "coach")
    @JsonIgnore
    private Club club;
    @OneToMany(mappedBy = "coach")
    @JsonIgnore
    private List<Player> playerList;
    private String coachName;
    private Integer coachAge;

    public Coach() {

    }

    public Long getId() {
        return coachId;
    }

    public void setId(Long id) {
        this.coachId = id;
    }

    public String getName() {
        return coachName;
    }

    public void setName(String name) {
        this.coachName = name;
    }

    public Integer getAge() {
        return coachAge;
    }

    public void setAge(Integer age) {
        this.coachAge = age;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Integer getCoachAge() {
        return coachAge;
    }

    public void setCoachAge(Integer coachAge) {
        this.coachAge = coachAge;
    }

    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

}