package com.coach.Coach.controller;

import com.coach.Coach.model.Coach;
import com.coach.Coach.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private CoachService coachService;
    @GetMapping
    public List<Coach> getAllCoaches() {return coachService.getAllCoaches();}
    @GetMapping("/{id}")
    public Optional<Coach> getCoachById (@PathVariable Long id) { return coachService.getCoachById(id); }

    @DeleteMapping("/{id}")
    public void deleteCoachById (@PathVariable Long id) { coachService.deleteCoachById(id); }

    @PostMapping
    public Optional<Coach> newCoach (@RequestBody Coach newCoach) { return coachService.createNewCoach(newCoach); }

    @PutMapping("completeUpdate/{id}")
     public ResponseEntity<String> coachEntityUpdate(@PathVariable Long id, @RequestBody Coach updateCoach) {
        Optional<Coach> coachToBeUpdated = coachService.coachEntityUpdate(id, updateCoach);
        if (coachToBeUpdated.isPresent()) {
            return ResponseEntity.ok("Coach Updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

 }
