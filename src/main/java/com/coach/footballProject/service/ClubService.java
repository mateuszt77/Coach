package com.coach.footballProject.service;

import com.coach.footballProject.model.Club;
import com.coach.footballProject.model.Coach;
import com.coach.footballProject.repository.ClubRepository;
import com.coach.footballProject.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private CoachRepository coachRepository;

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Optional<Club> getClubById(Long id) {
        return clubRepository.findById(id);
    }

    public void deleteClubById(Long id) {
        clubRepository.deleteById(id);
    }

    public Optional<Club> postNewClub(Club newClub) {
        if (newClub.getId() != null && clubRepository.existsById(newClub.getId())) {
            return Optional.empty();
        }
        return Optional.of(clubRepository.save(newClub));
    }

    public Optional<Club> completeClubEntityUpdated(Long clubId, Club updateClub) {
        if (clubRepository.existsById(clubId)) {
            updateClub.setId(clubId);
            return Optional.of(clubRepository.save(updateClub));
        }
        return Optional.empty();
    }

    public Optional<Club> createNewClub(Club newClub) {
        if (newClub.getId() != null && clubRepository.existsById(newClub.getId())) {
            return Optional.empty();
        }
        return Optional.of(clubRepository.save(newClub));
    }

    public Optional<Club> setCoachForClub(Long clubId, Long coachId) {
        if (clubRepository.existsById(clubId) && coachRepository.existsById(coachId)) {
            Club club = clubRepository.findById(clubId).get();
            Coach coach = coachRepository.findById(coachId).get();
            club.setCoach(coach);
            return Optional.of(clubRepository.save(club));
        }
        return Optional.empty();
    }
}
