package com.coach.Coach.controller;

import com.coach.Coach.model.Coach;
import com.coach.Coach.model.Stadium;
import com.coach.Coach.service.PlayerService;
import com.coach.Coach.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stadium")
public class StadiumController {

    private StadiumService stadiumService;
    @Autowired
    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }
    @GetMapping
    public List<Stadium> getAllStadiums() {return stadiumService.getAllStadiums();}

    @GetMapping("/{stadiumId}")
    public ResponseEntity<Stadium> getStadiumById(@PathVariable Long stadiumId) {
        Optional<Stadium> responseFromStadium = stadiumService.getStadiumById(stadiumId);
        if(responseFromStadium.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(responseFromStadium.get());
        }
    }
    @DeleteMapping("/{stadiumId}")
    public ResponseEntity<Void> deleteStadiumById(@PathVariable Long stadiumId) {
        if(stadiumService.getStadiumById(stadiumId).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            stadiumService.deleteStadiumById(stadiumId);
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping
    public ResponseEntity<Stadium> postNewStadium(@RequestBody Stadium newStadium) {
        Optional<Stadium> savedStadium = stadiumService.postNewStadium(newStadium);
        if(savedStadium.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStadium.get());
        }
    }
    @PutMapping("/completeUpdate/{stadiumId}")
    public ResponseEntity<String> completeStadiumEntityUpdate(@PathVariable Long stadiumId, @RequestBody Stadium updatedStadium) {
        Optional<Stadium> stadiumToBeUpdated = stadiumService.completeStadiumEntityUpdate(stadiumId, updatedStadium);
        if(stadiumToBeUpdated.isPresent()) {
            return ResponseEntity.ok("Stadium updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{stadiumId}/setPlayer/{playerId}")
    public ResponseEntity<String> setPLayerForStadium(@PathVariable Long stadiumId, @PathVariable Long playerId) {
        Optional<Stadium> playerForStadium = stadiumService.setPlayerForStadium(playerId, stadiumId);
        if(playerForStadium.isPresent()) {
            return ResponseEntity.ok("Stadium set for Player successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
