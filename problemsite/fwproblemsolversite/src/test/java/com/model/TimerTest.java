package com.model;

import static org.junit.Assert.*;
import org.junit.Test;

import com.fwproblemsolversite.problems.Timer;

public class TimerTest {

     // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // Tests that a new Timer object is initialized with the provided time limit and zero elapsed time
    @Test
    public void testTimerConstructorSetsTimeLimit() {
        Timer timer = new Timer(30.0);

        assertEquals(30.0, timer.toDouble(), 0.001);
        assertEquals(0.0, timer.getElapsedTime(), 0.001);
    }

    // Tests that start and stop methods correctly calculate elapsed time
    @Test
    public void testStartAndStopTimerProducesElapsedTime() throws InterruptedException {
        Timer timer = new Timer(30.0);

        timer.start();
        Thread.sleep(100);
        timer.stop();

        assertTrue(timer.getElapsedTime() > 0);
    }

    // Tests that stopping the timer without starting it does not change elapsed time from zero
    @Test
    public void testStopWithoutStartKeepsElapsedTimeAtZero() {
        Timer timer = new Timer(30.0);

        timer.stop();

        assertEquals(0.0, timer.getElapsedTime(), 0.001);
    }

    // Tests that isOverLimit returns false when elapsed time is below the time limit
    @Test
    public void testTimerIsNotOverLimitWhenBelowLimit() throws InterruptedException {
        Timer timer = new Timer(5.0);

        timer.start();
        Thread.sleep(100);
        timer.stop();

        assertFalse(timer.isOverLimit());
    }

    // Tests that isOverLimit returns true when elapsed time exceeds the time limit
    @Test
    public void testTimerIsOverLimitWhenLimitIsVerySmall() throws InterruptedException {
        Timer timer = new Timer(0.01);

        timer.start();
        Thread.sleep(100);
        timer.stop();

        assertTrue(timer.isOverLimit());
    }
}