package com.coach.Coach.service;

import com.coach.Coach.model.Club;
import com.coach.Coach.repository.ClubRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClubServiceTest {

    @Resource
    @InjectMocks
    private ClubService clubService;

    @Mock
    private ClubRepository clubRepository;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllClubs() {
    }

    @Test
    void getClubById() {
    }

    @Test
    void deleteClubById() {
    }

    @Test
    void postNewClub() {
    }

    @Test
    void completeClubEntityUpdated() {
    }

    @Test
    void createNewClub() {
    }

    @Test
    void setCoachForClub() {
    }
}