package com.coach.Coach.model;

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

    public void setPlayer(Object o) {
    }
}
