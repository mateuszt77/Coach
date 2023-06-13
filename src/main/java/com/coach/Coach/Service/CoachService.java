package com.coach.Coach.Service;

import com.coach.Coach.Coach;
import com.coach.Coach.Repository.CoachRepository;
import com.coach.Coach.controller.CoachController;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> getAllCoaches(){
        return coachRepository.findAll();
    }
}
