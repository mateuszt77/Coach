package com.coach.Coach.controller;


import com.coach.Coach.model.Club;
import com.coach.Coach.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/club")
public class ClubController {

    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    List<Club> getAllClubs() {
        return clubService.getAllClubs();
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<Club> getClubById(@PathVariable Long clubId) {
        Optional<Club> responseFromClub = clubService.getClubById(clubId);
        if (responseFromClub.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(responseFromClub.get());
        }
    }

    @DeleteMapping("/{clubId}")
    public ResponseEntity<Void> deleteClubById(@PathVariable Long clubId) {
        if (clubService.getClubById(clubId).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            clubService.deleteClubById(clubId);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Club> postNewClub(@RequestBody Club newClub) {
        Optional<Club> savedClub = clubService.postNewClub(newClub);
        if (savedClub.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedClub.get());
        }
    }

    @PutMapping("/completeUpdate/{clubId}")
    public ResponseEntity<String> completeClubEntityUpdate(@PathVariable Long clubId, @RequestBody Club updatedClub) {
        Optional<Club> clubToBeUpdated = clubService.completeClubEntityUpdated(clubId, updatedClub);
        if (clubToBeUpdated.isPresent()) {
            return ResponseEntity.ok("Club updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{clubId}/setCoach/{coachId}")
    public ResponseEntity<String> setCoachForClub(@PathVariable Long clubId, @PathVariable Long coachId) {
        Optional<Club> club = clubService.setCoachForClub(clubId, coachId);
        if (club.isPresent()) {
            return ResponseEntity.ok("Coach was set successfully");
        }
        return ResponseEntity.notFound().build();
    }
}