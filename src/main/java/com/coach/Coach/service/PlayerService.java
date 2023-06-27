package com.coach.Coach.service;

import com.coach.Coach.model.Coach;
import com.coach.Coach.model.Player;
import com.coach.Coach.model.Stadium;
import com.coach.Coach.repository.CoachRepository;
import com.coach.Coach.repository.PlayerRepository;
import com.coach.Coach.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private StadiumRepository stadiumRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public void deletePlayerById(Long playerId) {
        Optional<Player> playerToDelete = playerRepository.findById(playerId);
        if (playerToDelete.isPresent()) {

            playerRepository.deleteById(playerId);
        }
    }

    public Optional<Player> completePlayerEntityUpdate(Long playerId, Player updatePlayer) {
        if (playerRepository.existsById(playerId)) {
            updatePlayer.setId(playerId);
            return Optional.of(playerRepository.save(updatePlayer));
        }
        return Optional.empty();
    }

    public Optional<Player> createNewPlayer(Player newPlayer) {
        if (playerRepository.existsById(newPlayer.getId())) {
            return Optional.empty();
        }
        return Optional.of(playerRepository.save(newPlayer));
    }

    public Optional<Player> postNewPlayer(Player newPlayer) {
        if (newPlayer.getId() != null && playerRepository.existsById(newPlayer.getId())) {
            return Optional.empty();
        }
        return Optional.of(playerRepository.save(newPlayer));
    }

    public Optional<Player> setStadiumForPlayer(Long stadiumId, Long playerId) {
        if (stadiumRepository.existsById(stadiumId) && playerRepository.existsById(playerId)) {
            Stadium stadium = stadiumRepository.findById(stadiumId).get();
            Player player = playerRepository.findById(playerId).get();
            player.addStadium(stadium);
            return Optional.of(playerRepository.save(player));
        }
        return Optional.empty();
    }
}


