package com.model;

import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;

import com.fwproblemsolversite.data.Progress;
import com.fwproblemsolversite.data.Achievement;
import com.fwproblemsolversite.enums.Difficulty;

public class ProgressTest {

     // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // Tests that a new Progress object is initialized with default values
    @Test
    public void testProgressConstructorSetsDefaultValues() {
        Progress progress = new Progress();

        assertEquals(0, progress.getProblemsSolved());
        assertEquals(0, progress.getEasySolved());
        assertEquals(0, progress.getMediumSolved());
        assertEquals(0, progress.getHardSolved());
        assertEquals(0, progress.getTotalPoints());
        assertEquals(0, progress.getDailyStreak());
        assertNotNull(progress.getLastActiveDate());
    }

    // Tests that updateProgress with a valid problem ID and EASY difficulty updates the counts and points correctly
    @Test
    public void testUpdateProgressEasyUpdatesCountsAndPoints() {
        Progress progress = new Progress();
        progress.setLastActiveDate(LocalDate.now().minusDays(1));
        progress.setDailyStreak(3);

        progress.updateProgress("1", Difficulty.EASY);

        assertEquals(1, progress.getProblemsSolved());
        assertEquals(1, progress.getEasySolved());
        assertEquals(10, progress.getTotalPoints());
        assertEquals(4, progress.getDailyStreak());
    }

    // Tests that updateProgress with a valid problem ID and MEDIUM difficulty updates the counts and points correctly
    @Test
    public void testUpdateProgressMediumUpdatesCountsAndPoints() {
        Progress progress = new Progress();
        progress.setLastActiveDate(LocalDate.now().minusDays(1));

        progress.updateProgress("2", Difficulty.MEDIUM);

        assertEquals(2, progress.getProblemsSolved());
        assertEquals(2, progress.getMediumSolved());
        assertEquals(40, progress.getTotalPoints());
    }

    // Tests that updateProgress with a valid problem ID and HARD difficulty updates the counts and points correctly
    @Test
    public void testUpdateProgressHardUpdatesCountsAndPoints() {
        Progress progress = new Progress();
        progress.setLastActiveDate(LocalDate.now().minusDays(1));

        progress.updateProgress("1", Difficulty.HARD);

        assertEquals(1, progress.getProblemsSolved());
        assertEquals(1, progress.getHardSolved());
        assertEquals(30, progress.getTotalPoints());
    }

    // Tests that updateProgress with an invalid input does not change any counts or points
    @Test
    public void testUpdateProgressInvalidStringDoesNotChangeValues() {
        Progress progress = new Progress();

        progress.updateProgress("abc", Difficulty.EASY);

        assertEquals(0, progress.getProblemsSolved());
        assertEquals(0, progress.getEasySolved());
        assertEquals(0, progress.getTotalPoints());
    }

    // Tests that null difficulty does not change any progress.
    @Test
    public void testUpdateProgressNullDifficultyDoesNotChangeValues() {
        Progress progress = new Progress();

        progress.updateProgress("1", null);

        assertEquals(0, progress.getProblemsSolved());
        assertEquals(0, progress.getTotalPoints());
    }

   // Tests that empty string input does not change any counts or points
    @Test
    public void testUpdateProgressWithZeroDoesNotChangeValues() {
        Progress progress = new Progress();

        progress.updateProgress("0", Difficulty.EASY);

        assertEquals(0, progress.getProblemsSolved());
        assertEquals(0, progress.getEasySolved());
        assertEquals(0, progress.getTotalPoints());
    }

     // Test that adding an achievement stores it correctly in the progress object
    @Test
    public void testAddAchievementAddsAchievementToList() {
        Progress progress = new Progress();
        Achievement achievement = new Achievement(new ArrayList<Integer>());

        progress.addAchievement(achievement);

        assertEquals(1, progress.getAchievements().size());
    }

    //Test that streak resets to 1 if the last active date is more than 2 days ago when updating progress
    @Test
    public void testUpdateProgressResetsStreakIfLastActiveDateTooOld() {
        Progress progress = new Progress();
        progress.setDailyStreak(8);
        progress.setLastActiveDate(LocalDate.now().minusDays(3));

        progress.updateProgress("1", Difficulty.EASY);

        assertEquals(1, progress.getDailyStreak());
    }
}