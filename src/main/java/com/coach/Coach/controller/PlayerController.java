package com.coach.Coach.controller;


import com.coach.Coach.model.Coach;
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
    public PlayerController (PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {return playerService.getAllPlayers();}

    @GetMapping("/{playerId}")
    public Optional<Player> getPlayerById (@PathVariable Long id) { return playerService.getPlayerById(id); }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Long playerId) {
        if(playerService.getPlayerById(playerId).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            playerService.deletePlayerById(playerId);
            return ResponseEntity.noContent().build();
        }
    }
        @PostMapping
    public ResponseEntity<Player> postNewPlayer(@RequestBody Player newPlayer) {
        Optional <Player> savedPlayer = playerService.postNewPlayer(newPlayer);
        if(savedPlayer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer.get());
        }
    }
//    @PostMapping
//    public Optional<Player> newPlayer (@RequestBody Player newPlayer) { return playerService.createNewPlayer(newPlayer); }
}


