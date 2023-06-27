package com.coach.Coach.controller;


import com.coach.Coach.model.Player;
import com.coach.Coach.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId) {
        Optional<Player> responseFromPlayer = playerService.getPlayerById(playerId);
        if (responseFromPlayer.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(responseFromPlayer.get());
        }
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Long playerId) {
        if (playerService.getPlayerById(playerId).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            playerService.deletePlayerById(playerId);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Player> postNewPlayer(@RequestBody Player newPlayer) {
        Optional<Player> savedPlayer = playerService.postNewPlayer(newPlayer);
        if (savedPlayer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer.get());
        }
    }

    @PutMapping("/completeUpdate/{playerId}")
    public ResponseEntity<String> completePlayerEntityUpdate(@PathVariable Long playerId, @RequestBody Player updatedPlayer) {
        Optional<Player> playerToBeUpdated = playerService.completePlayerEntityUpdate(playerId, updatedPlayer);
        if (playerToBeUpdated.isPresent()) {
            return ResponseEntity.ok("Player updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{playerId}/setStadium/{stadiumId}")
    public ResponseEntity<String> setStadiumForPlayer(@PathVariable Long stadiumId, @PathVariable Long playerId) {
        Optional<Player> stadiumForPlayer = playerService.setStadiumForPlayer(stadiumId, playerId);
        if (stadiumForPlayer.isPresent()) {
            return ResponseEntity.ok("Stadium set for Player successfully");
        }
        return ResponseEntity.notFound().build();
    }
}


