package com.coach.Coach.service;


import com.coach.Coach.model.Club;
import com.coach.Coach.model.Coach;
import com.coach.Coach.repository.ClubRepository;
import com.coach.Coach.repository.CoachRepository;
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
    @Mock
    private CoachRepository coachRepository;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllClubsReturnListOfClubsFromRepository() {
        List<Club> clubList = new ArrayList<>();
        clubList.add(new Club(1L, "Real", 121));
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
    void postNewClubShouldSaveNewClub() {
        Club club = new Club(30L, "Ajax", 123);
        when(clubRepository.save(club)).thenReturn(club);
        Optional<Club> savedClub = this.clubService.postNewClub(club);
        assertEquals(savedClub.get(), club);
    }

    @Test
    void completeClubEntityUpdatedShouldUpdateClubName() {
        Long clubId = 1L;
        Club existingClub = new Club();
        existingClub.setId(clubId);

        Club updatedClub = new Club();
        updatedClub.setId(clubId);
        updatedClub.setName("Club is Updated");

        when(clubRepository.existsById(clubId)).thenReturn(true);
        when(clubRepository.save(updatedClub)).thenReturn(updatedClub);

        Optional<Club> result = clubService.completeClubEntityUpdated(clubId, updatedClub);

        verify(clubRepository, times(1)).existsById(clubId);
        verify(clubRepository, times(1)).save(updatedClub);
    }

    @Test
    void shouldSetCoachForClub() {
        Long clubId = 1L;
        Long coachId = 1L;
        Club club = new Club();
        Coach coach = new Coach();

        when(clubRepository.findById(clubId)).thenReturn(Optional.of(club));
        when(coachRepository.findById(coachId)).thenReturn(Optional.of(coach));
        when(clubRepository.existsById(clubId)).thenReturn(true);
        when(coachRepository.existsById(coachId)).thenReturn(true);
        club.setCoach(coach);
        when(clubRepository.save(club)).thenReturn(club);

        clubService.setCoachForClub(clubId, coachId);

        verify(clubRepository).save(club);

//        Optional<Club> optionalClub = clubService.setCoachForClub(clubId, coachId);
//
//        Assertions.assertEquals(club, optionalClub.get());
//        Assertions.assertEquals(coach, club.getCoach());
    }
}
