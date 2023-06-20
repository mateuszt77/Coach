package com.coach.Coach.service;

import com.coach.Coach.model.Coach;
import com.coach.Coach.model.Stadium;
import com.coach.Coach.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StadiumService {

    @Autowired
    private StadiumRepository stadiumRepository;

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }
    public Optional<Stadium> getStadiumById(Long id) {
        return stadiumRepository.findById(id);
    }
    public void deleteStadiumById(Long id) {
        stadiumRepository.deleteById(id);
    }
    public Optional<Stadium> createNewStadium(Stadium newStadium) {
        if (stadiumRepository.existsById(newStadium.getId())) {
            return Optional.empty();
        }
        return Optional.of(stadiumRepository.save(newStadium));
    }
}
