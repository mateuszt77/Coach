package com.coach.Coach.repository;

import com.coach.Coach.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long > {
//    List<Club> findAllByNameInclude(String name);
}
