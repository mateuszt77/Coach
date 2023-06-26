package com.coach.Coach.service;

import com.coach.Coach.model.Coach;
import com.coach.Coach.model.Player;
import com.coach.Coach.repository.CoachRepository;
import com.coach.Coach.repository.PlayerRepository;
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

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

        public void deletePlayerById(Long playerId) {
        Optional<Player> playerToDelete = playerRepository.findById(playerId);
        if(playerToDelete.isPresent()) {
            for (Coach coach : playerToDelete.get().getCoaches()) {
                coach.setPlayer(null);
                coachRepository.save(coach);
            }
            playerRepository.deleteById(playerId);
        }
    }
        public Optional<Player> postNewPlayer(Player newPlayer) {
        if(newPlayer.getId() != null && playerRepository.existsById(newPlayer.getId())) {
            return Optional.empty();
        }
        return Optional.of(playerRepository.save(newPlayer));
    }

    public Optional<Player> createNewPlayer(Player newPlayer) {
        if (playerRepository.existsById(newPlayer.getId())) {
            return Optional.empty();
        }
        return Optional.of(playerRepository.save(newPlayer));
    }
}


