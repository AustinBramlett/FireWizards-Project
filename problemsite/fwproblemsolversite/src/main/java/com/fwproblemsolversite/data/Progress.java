package com.fwproblemsolversite.data;

import java.time.LocalDate;
import java.util.ArrayList;
import com.fwproblemsolversite.enums.Difficulty;

public class Progress {

    private int problemsSolved;
    private int easySolved;
    private int mediumSolved;
    private int hardSolved; 
    private int totalPoints;
    private int dailyStreak;
    private LocalDate lastActiveDate;
    private ArrayList<Achievement> achievements;

    // Constructor to initialize progress with default values
    public Progress() {
        this.problemsSolved = 0;
        this.easySolved = 0;
        this.mediumSolved = 0;
        this.hardSolved = 0;
        this.totalPoints = 0;
        this.dailyStreak = 0;
        this.lastActiveDate = LocalDate.now();
        this.achievements = new ArrayList<>();
    }

    // Method to get a summary of the user's progress
    public String getProgress() {
        return "Problems Solved: " + problemsSolved + "\n" +
               "Easy Solved: " + easySolved + "\n" +
               "Medium Solved: " + mediumSolved + "\n" +
               "Hard Solved: " + hardSolved + "\n" +
               "Total Points: " + totalPoints + "\n" +
               "Daily Streak: " + dailyStreak;
    }
    
    /**
     * Method to update the user's progress based on the difficulty of the problem solved.
     * The value parameter is expected to be a string representation of an integer indicating how many problems were solved.
     * The type parameter indicates the difficulty level of the problems solved (EASY, MEDIUM, HARD).
     */
    public void updateProgress(String value, Difficulty type) {
        if (value == null || type == null) return;

        try { 
            int increment = Integer.parseInt(value);

            if(increment > 0) {
                problemsSolved += increment;

                switch (type) {
                    case EASY:
                        easySolved += increment;
                        totalPoints += increment * 10; // Example point calculation
                        break;
                    case MEDIUM:
                        mediumSolved += increment;
                        totalPoints += increment * 20; // Example point calculation
                        break;
                    case HARD:
                        hardSolved += increment;
                        totalPoints += increment * 30; // Example point calculation
                        break;
                }

                updateStreak();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid value for progress update: " + value);
        }
    }
    
    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    private void updateStreak() {
        LocalDate today = LocalDate.now();

        if (lastActiveDate == null) {
            dailyStreak = 1;
        }
        else if (lastActiveDate.plusDays(1).equals(today)) {
            dailyStreak++;
        } else if (!lastActiveDate.equals(today)) {
            dailyStreak = 1; // Reset streak if not consecutive
        }

        lastActiveDate = today;
    }

    // Method to get progress stats as a list of integer arrays
    public ArrayList<Integer> getStats() {
        ArrayList<Integer> stats = new ArrayList<>();
        stats.add(problemsSolved);
        stats.add(dailyStreak);
        return stats;
    }

    // Getters for progress attributes
    public int getProblemsSolved() {
        return problemsSolved;
    }

    public int getEasySolved() {
        return easySolved;
    }

    public int getMediumSolved() {
        return mediumSolved;
    }

    public int getHardSolved() {
        return hardSolved;
    }

    public int getTotalPoints() {
        return totalPoints;
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

    // Setters for progress attributes
    public void setProblemsSolved(int problemsSolved) {
        this.problemsSolved = problemsSolved;
    }

    public void setEasySolved(int easySolved) {
        this.easySolved = easySolved;
    }

    public void setMediumSolved(int mediumSolved) {
        this.mediumSolved = mediumSolved;
    }

    public void setHardSolved(int hardSolved) {
        this.hardSolved = hardSolved;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

     public void setDailyStreak(int dailyStreak) {
        this.dailyStreak = dailyStreak;
    }

    public void setLastActiveDate(LocalDate lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

}