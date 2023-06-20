package com.coach.Coach.service;

import com.coach.Coach.model.Coach;
import com.coach.Coach.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Optional<Coach> getCoachById(Long id) {
        return coachRepository.findById(id);
    }

    public void deleteCoachById(Long id) {
        coachRepository.deleteById(id);
    }

    public Optional<Coach> createNewCoach(Coach newCoach) {
        if (coachRepository.existsById(newCoach.getId())) {
            return Optional.empty();
        }
            return Optional.of(coachRepository.save(newCoach));
    }
    public Optional<Coach> coachEntityUpdate (Long id, Coach updateCoach) {
        if (coachRepository.existsById(id)) {
            updateCoach.setId(id);
            return Optional.of(coachRepository.save(updateCoach));
        }
        return Optional.empty();
    }

//    public Optional<Coach> coachEntityUpdate(Long id, String name, Integer age);
//
//    {
//        return Optional.empty();
//    }

    // public Optional<Coach> putCoachById(Long id) {return coachRepository.findById(id); }

}

//

