package com.coach.Coach.service;

import com.coach.Coach.model.Club;
import com.coach.Coach.model.Coach;
import com.coach.Coach.model.Player;
import com.coach.Coach.repository.ClubRepository;
import com.coach.Coach.repository.CoachRepository;
import com.coach.Coach.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public CoachService(CoachRepository coachRepository, ClubRepository clubRepository) {
        this.coachRepository = coachRepository;
        this.clubRepository = clubRepository;
    }


    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Optional<Coach> getCoachById(Long id) {
        return coachRepository.findById(id);
    }


    public void deleteCoachById(Long coachId) {
        Optional<Coach> coachToDelete = coachRepository.findById(coachId);
        if (coachToDelete.isPresent()) {
            Club club = coachToDelete.get().getClub();
            if (club != null) {
                club.setCoach(null);
                clubRepository.save(club);
            }
            coachRepository.deleteById(coachId);
        }
    }

    public Optional<Coach> postNewCoach(Coach newCoach) {
        if (newCoach.getId() != null && coachRepository.existsById(newCoach.getId())) {
            return Optional.empty();
        }
        return Optional.of(coachRepository.save(newCoach));
    }

    public Optional<Coach> createNewCoach(Coach newCoach) {
        if (newCoach.getId() != null && coachRepository.existsById(newCoach.getId())) {
            return Optional.empty();
        }
        return Optional.of(coachRepository.save(newCoach));
    }

    public Optional<Coach> coachEntityUpdate(Long id, Coach updateCoach) {
        if (coachRepository.existsById(id)) {
            updateCoach.setId(id);
            return Optional.of(coachRepository.save(updateCoach));
        }
        return Optional.empty();
    }

    public boolean setPlayersForCoach(Long coachId, Long playerId) {
        if (coachRepository.existsById(coachId) && playerRepository.existsById(playerId)) {
            Coach coach = coachRepository.findById(coachId).get();
            Player player = playerRepository.findById(playerId).get();
            player.setCoach(coach);
            playerRepository.save(player);
            return true;
        }
        return false;
    }
}


