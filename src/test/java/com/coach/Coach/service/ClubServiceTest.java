package com.coach.Coach.service;


import com.coach.Coach.model.Club;
import com.coach.Coach.repository.ClubRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClubServiceTest {

    @InjectMocks
    private ClubService clubService;

    @Mock
    private ClubRepository clubRepository;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllClubsReturnListOfClubsFromRepository() {
        List<Club> clubList = new ArrayList<>();
        clubList.add(new Club(1L, "Real", 123));
        clubList.add(new Club(2L, "Inter", 111));
        when(clubRepository.findAll()).thenReturn(clubList);

        List<Club> realClubList = clubService.getAllClubs();
        assertEquals(clubList, realClubList);
    }

    @Test
    void getClubByIdShouldReturnClub() {
        Club club = new Club();
        Mockito.when(clubRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(club));

        Optional<Club> retrievedClub = clubService.getClubById(1L);

        Assertions.assertTrue(retrievedClub.isPresent());
        assertEquals(club, retrievedClub.get());
    }

    @Test
    void deleteClubByIdShouldRemoveClub() {
        Long clubId = 1L;
        Club clubToDelete = new Club();
        clubToDelete.setId(clubId);

        doNothing().when(clubRepository).deleteById(clubId);
        clubService.deleteClubById(clubId);
        verify(clubRepository, times(1)).deleteById(clubId);
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

