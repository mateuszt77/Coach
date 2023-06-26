package com.coach.Coach.controller;

import com.coach.Coach.model.Coach;
import com.coach.Coach.model.Stadium;
import com.coach.Coach.service.PlayerService;
import com.coach.Coach.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stadium")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @GetMapping
    public List<Stadium> getAllStadiums() {return stadiumService.getAllStadiums();}

    @GetMapping("/{stadiumId}")
    public Optional<Stadium> getStadiumById (@PathVariable Long id) { return stadiumService.getStadiumById(id); }

    @DeleteMapping("/{stadiumId}")
    public void deleteStadiumById (@PathVariable Long id) { stadiumService.deleteStadiumById(id); }

    @PostMapping
    public Optional<Stadium> newStadium (@RequestBody Stadium newStadium) { return stadiumService.createNewStadium(newStadium); }

}
