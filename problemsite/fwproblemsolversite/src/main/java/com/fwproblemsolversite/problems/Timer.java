package com.fwproblemsolversite.problems;

import java.time.Duration;
import java.time.LocalTime;

public class Timer {

    private LocalTime startTime;
    private LocalTime stopTime;
    private double elapsedTime;
    private double timeLimit;

    public Timer(Double time) {
        this.timeLimit = (time == null) ? 0.0 : time;
        this.elapsedTime = 0.0;
    }

    public void start() {
        startTime = LocalTime.now();
        stopTime = null;
        elapsedTime = 0.0;
    }

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

    public boolean isOverLimit() {
        return timeLimit > 0 && elapsedTime > timeLimit;
    }

    public double toDouble() {
        return timeLimit;
    }
}