package com.coach.Coach.controller;


import com.coach.Coach.model.Coach;
import com.coach.Coach.model.Player;
import com.coach.Coach.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers() {return playerService.getAllPlayers();}

    @GetMapping("/{id}")
    public Optional<Player> getPlayerById (@PathVariable Long id) { return playerService.getPlayerById(id); }

    @DeleteMapping("/{id}")
    public void deletePlayerById (@PathVariable Long id) { playerService.deletePlayerById(id); }

    @PostMapping
    public Optional<Player> newPlayer (@RequestBody Player newPlayer) { return playerService.createNewPlayer(newPlayer); }
}
