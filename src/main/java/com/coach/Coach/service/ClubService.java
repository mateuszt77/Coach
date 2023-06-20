package com.coach.Coach.service;

import com.coach.Coach.model.Club;
import com.coach.Coach.model.Coach;
import com.coach.Coach.repository.ClubRepository;
import com.coach.Coach.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }
    public Optional<Club> getClubById(Long id) {
        return clubRepository.findById(id);
    }
    public void deleteClubById(Long id) {
        clubRepository.deleteById(id);
    }
    public Optional<Club> newClub(Club newClub) {
        if (clubRepository.existsById(newClub.getId())) {
            return Optional.empty();
        }
        return Optional.of(clubRepository.save(newClub));
    }
}
