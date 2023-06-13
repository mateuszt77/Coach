package com.coach.Coach.controller;

import com.coach.Coach.Coach;
import com.coach.Coach.Repository.CoachRepository;
import com.coach.Coach.Service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coach")
public class CoachController {
    @Autowired
    private CoachService coachService;
    @GetMapping
    public List <Coach> getAllCoaches() {
        return coachService.getAllCoaches();
    }
}
