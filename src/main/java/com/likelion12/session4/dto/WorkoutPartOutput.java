package com.likelion12.session4.dto;

import java.util.List;

public class WorkoutPartOutput {
    public List<WorkoutOutput> workoutLogs;
    public int maxPage;

    public WorkoutPartOutput(List<WorkoutOutput> workoutLogs, int maxPage) {
        this.workoutLogs = workoutLogs;
        this.maxPage = maxPage;
    }
}