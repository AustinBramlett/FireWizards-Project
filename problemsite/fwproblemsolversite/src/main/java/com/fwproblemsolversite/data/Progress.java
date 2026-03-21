package com.fwproblemsolversite.data;

import java.time.LocalDate;
import java.util.ArrayList;
import com.fwproblemsolversite.enums.ProblemType;

public class Progress {

    private int problemsSolved;
    private int dailyStreak;
    private LocalDate lastActiveDate;
    private ArrayList<Achievement> achievements;

    // Constructor to initialize progress with default values
    public Progress() {
        this.problemsSolved = 0;
        this.dailyStreak = 0;
        this.lastActiveDate = LocalDate.now();
        this.achievements = new ArrayList<>();
    }

    // Method to get a summary of the user's progress
    public String getProgress() {
        return "Problems Solved: " + problemsSolved + ", Daily Streak: " + dailyStreak;
    }
    
    /**
     * Method to update progress based on the type of problem solved
     * @param value The value to update the progress with (e.g., number of problems solved)
     */
    public void updateProgress(String value, ProblemType type) {
        if (value == null || type == null) return;

        try { 
            int increment = Integer.parseInt(value);

            if (increment > 0) {
                problemsSolved += increment;

                LocalDate today = LocalDate.now();

                if (lastActiveDate == null) {
                    dailyStreak = 1;
                } else if (lastActiveDate.equals(today)) {
                    // Same day, do not change streak
                } else if (lastActiveDate.plusDays(1).equals(today)) {
                    dailyStreak++;
                } else {
                    dailyStreak = 1; // Reset streak if more than 1 day has passed
                }
                // Update last active date to today
                lastActiveDate = today;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid value for progress update: " + value);
        }
    }
    
    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    // Method to get progress stats as a list of integer arrays
    protected ArrayList<int[]> getStats() {
        ArrayList<int[]> stats = new ArrayList<>();
        stats.add(new int[]{problemsSolved, dailyStreak});
        return stats;
    }

    // Getters for progress attributes
    public int getProblemsSolved() {
        return problemsSolved;
    }

    public int getDailyStreak() {
        return dailyStreak;
    }

    public LocalDate getLastActiveDate() {
        return lastActiveDate;
    }

    // Method to add an achievement to the user's progress
    public void addAchievement(Achievement achievement) {
        if (achievement != null) {
            achievements.add(achievement);
        }
    }

}