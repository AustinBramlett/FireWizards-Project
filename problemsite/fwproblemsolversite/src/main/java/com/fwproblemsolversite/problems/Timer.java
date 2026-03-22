package com.fwproblemsolversite.problems;

import java.time.Duration;
import java.time.LocalTime;
/**
 * Represents a timer used to track how long a user will take to solve the problem.
 * 
 * The timer can be started and stopped.
 */
public class Timer {

    private LocalTime startTime;
    private LocalTime stopTime;
    private double elapsedTime;
    private double timeLimit;
    
    /**
     * Constructor for the Timer class
     * @param time The time limit for the timer
     */
    public Timer(Double time) {
        this.timeLimit = (time == null) ? 0.0 : time;
        this.elapsedTime = 0.0;
    }

    /**
     * Method to start the timer
     */
    public void start() {
        startTime = LocalTime.now();
        stopTime = null;
        elapsedTime = 0.0;
    }

    /**
     * Stops the timer and will calculate the elapsed time.
     */
    public void stop() {
        if (startTime == null) {
            elapsedTime = 0.0;
            return;
        }
        stopTime = LocalTime.now();
        elapsedTime = Duration.between(startTime, stopTime).toMillis() / 1000.0;
    }

    /**
     * Returns the elapsed time in seconds.
     * 
     * @return The elapsed time in seconds.
     */
    public double getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Checks if the elapsed time has exceeded the time limit.
     * 
     * @return true if over limit, if not false.
     */
    public boolean isOverLimit() {
        return timeLimit > 0 && elapsedTime > timeLimit;
    }

    /**
     * Returns the time limit as a double.
     * 
     * @return time limit
     */
    public double toDouble() {
        return timeLimit;
    }
}