package com.fwproblemsolversite.problems;

import java.time.Duration;
import java.time.LocalTime;

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

    // Method to start the timer
    public void start() {
        startTime = LocalTime.now();
        stopTime = null;
        elapsedTime = 0.0;
    }

    // Method to stop the timer and calculate elapsed time
    public void stop() {
        if (startTime == null) {
            elapsedTime = 0.0;
            return;
        }
        stopTime = LocalTime.now();
        elapsedTime = Duration.between(startTime, stopTime).toMillis() / 1000.0;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    // Method to check if the elapsed time has exceeded the time limit
    public boolean isOverLimit() {
        return timeLimit > 0 && elapsedTime > timeLimit;
    }

    // Method to convert the time limit to a double value
    public double toDouble() {
        return timeLimit;
    }
}