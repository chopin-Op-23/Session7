package com.likelion12.session4.repository;

import com.likelion12.session4.dto.WorkoutOutput;
import com.likelion12.session4.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
