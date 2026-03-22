package com.fwproblemsolversite.data;

import java.time.LocalDate;
import java.util.ArrayList;
import com.fwproblemsolversite.enums.Difficulty;
/**
 * Represents the progress of a user in the system.
 * 
 * Tracks the problems solved by their difficulty, total points, daily streak,
 * their last active date, and the achievements the user has earned.
 */
public class Progress {

    private int problemsSolved;
    private int easySolved;
    private int mediumSolved;
    private int hardSolved; 
    private int totalPoints;
    private int dailyStreak;
    private LocalDate lastActiveDate;
    private ArrayList<Achievement> achievements;

    /**
     * Creates a new Progress object with all default values.
     */
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
    /**
     * Creates a progress object useing saved data.
     * 
     * @param data A list of integers that show progress values
     * @param date The last active date as a string
     */
    public Progress(ArrayList<Integer> data, String date){
        this.problemsSolved = data.get(0);
        this.easySolved = data.get(1);
        this.mediumSolved = data.get(2);
        this.hardSolved = data.get(3);
        this.totalPoints = data.get(4);
        this.dailyStreak = data.get(5);
        this.lastActiveDate = LocalDate.parse(date);
    }
    /**
     * Returns progress data as a list of integers.
     * 
     * @return list containing progress values
     */
    public ArrayList<Integer> getProgressDataList(){
        ArrayList<Integer> ProgressData = new ArrayList<Integer>();
        ProgressData.add(problemsSolved);
        ProgressData.add(easySolved);
        ProgressData.add(mediumSolved);
        ProgressData.add(hardSolved);
        ProgressData.add(totalPoints);
        ProgressData.add(dailyStreak);
        return ProgressData;
    }
    /**
     * Returns the last active date as a string.
     * 
     * @return The last active date as a string.
     */
    public String getLastActiveString() {
        return lastActiveDate.toString();
    }
    /**
     * Returns a summary of the users progress.
     * 
     * @return string showing progress details
     */
    public String getProgress() {
        return "Problems Solved: " + problemsSolved + "\n" +
               "Easy Solved: " + easySolved + "\n" +
               "Medium Solved: " + mediumSolved + "\n" +
               "Hard Solved: " + hardSolved + "\n" +
               "Total Points: " + totalPoints + "\n" +
               "Daily Streak: " + dailyStreak;
    }
    
    /**
     * Updates the users progresss based on the number of problems they solved
     * as well as their difficulty level.
     * 
     * @param value String representing the number of problems solved 
     * @param type The difficulty level of the problems solved
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
    /**
     * Returns the list of achievements the user has earned.
     * 
     * @return The list of achievements.
     */
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

    /**
     * Returns progress stats.
     * 
     * @return list containing the problems solved and the daily streak
     */
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

    /**
     * Adds an achievement to the users progress.
     * 
     * @param achievement The achievement to add
     */
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