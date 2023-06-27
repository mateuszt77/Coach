package com.coach.Coach.service;

import com.coach.Coach.model.Coach;
import com.coach.Coach.model.Player;
import com.coach.Coach.model.Stadium;
import com.coach.Coach.repository.PlayerRepository;
import com.coach.Coach.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StadiumService {

    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    public PlayerRepository playerRepository;

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }
    public Optional<Stadium> getStadiumById(Long id) {
        return stadiumRepository.findById(id);
    }
    public void deleteStadiumById(Long id) {
        stadiumRepository.deleteById(id);
    }

    public Optional<Stadium> postNewStadium(Stadium newStadium) {
        if(newStadium.getId() != null && stadiumRepository.existsById(newStadium.getId())) {
            return Optional.empty();
        }
        return Optional.of(stadiumRepository.save(newStadium));
    }
    public Optional<Stadium> completeStadiumEntityUpdate(Long stadiumId, Stadium updateStadium) {
        if(stadiumRepository.existsById(stadiumId)) {
            updateStadium.setId(stadiumId);
            return Optional.of(stadiumRepository.save(updateStadium));
        }
        return Optional.empty();
    }
    public Optional<Stadium> setPlayerForStadium(Long playerId, Long stadiumId) {
        if(playerRepository.existsById(playerId) && stadiumRepository.existsById(stadiumId)) {
            Stadium stadium = stadiumRepository.findById(stadiumId).get();
            Player player = playerRepository.findById(playerId).get();
            stadium.addPlayer(player);
            return Optional.of(stadiumRepository.save(stadium));
        }
        return Optional.empty();
    }
    public Optional<Stadium> createNewStadium(Stadium newStadium) {
        if (stadiumRepository.existsById(newStadium.getId())) {
            return Optional.empty();
        }
        return Optional.of(stadiumRepository.save(newStadium));
    }
}