package com.likelion12.session4.dto;

import java.time.LocalDateTime;

public class WorkoutInput {
    public Long id;
    public String exerciseName;
    public String duration;
    public LocalDateTime date;
    public String content;

    public Long getId() {
        return id;
    }
    public String getExerciseName(){
        return exerciseName;
    }
    public String getDuration(){
        return duration;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public String getContent() {
        return content;
    }

}
