package com.coach.footballProject.controller;

import com.coach.footballProject.model.Coach;
import com.coach.footballProject.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coach")
public class CoachController {
    private CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    public List<Coach> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    @GetMapping("/{coachId}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long coachId) {
        Optional<Coach> responseFromCoach = coachService.getCoachById(coachId);
        if (responseFromCoach.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(responseFromCoach.get());
        }
    }

    @DeleteMapping("/{coachId}")
    public ResponseEntity<Void> deleteCoachById(@PathVariable Long coachId) {
        if (coachService.getCoachById(coachId).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            coachService.deleteCoachById(coachId);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Coach> postNewCoach(@RequestBody Coach newCoach) {
        Optional<Coach> savedCoach = coachService.postNewCoach(newCoach);
        if (savedCoach.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCoach.get());
        }
    }

    @PutMapping("completeUpdate/{coachId}")
    public ResponseEntity<String> coachEntityUpdate(@PathVariable Long coachId, @RequestBody Coach updateCoach) {
        Optional<Coach> coachToBeUpdated = coachService.coachEntityUpdate(coachId, updateCoach);
        if (coachToBeUpdated.isPresent()) {
            return ResponseEntity.ok("Coach Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{coachId}/setPlayer/{playerId}")
    public ResponseEntity<String> setPlayersForCoach(@PathVariable Long coachId, @PathVariable Long playerId) {
        boolean coachIsSetForPlayer = coachService.setPlayersForCoach(coachId, playerId);
        if (coachIsSetForPlayer) {
            return ResponseEntity.ok("Players set for Coach successfully");
        }
        return ResponseEntity.notFound().build();
    }
}