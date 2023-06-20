package com.coach.Coach.controller;


import com.coach.Coach.model.Club;
import com.coach.Coach.model.Coach;
import com.coach.Coach.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;
    @GetMapping
    List<Club> getAllClubs() {return clubService.getAllClubs();}

    @GetMapping("/{id}")
    public Optional<Club> getClubById (@PathVariable Long id) { return clubService.getClubById(id); }
    @DeleteMapping("/{id}")
    public void deleteClubById (@PathVariable Long id) { clubService.deleteClubById(id); }

    @PostMapping
    public Optional<Club> newClub (@RequestBody Club newClub) { return clubService.newClub(newClub); }

}
