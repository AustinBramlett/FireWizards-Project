package com.model;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

import com.fwproblemsolversite.data.Achievement;

public class AchievementTest {

     // make sure the test is working
    @Test
    public void testBasic() {
        assertTrue(true);
    }

    // Tests that a new Achievement object is initialized with default values
    @Test
    public void testAchievementConstructorSetsDefaultValues() {
        ArrayList<Integer> req = new ArrayList<Integer>();
        req.add(5);

        Achievement achievement = new Achievement(req);

        assertEquals("Achievement", achievement.getTitle());
        assertEquals("", achievement.getDescription());
        assertEquals(1, achievement.getRequirements().size());
        assertEquals(Integer.valueOf(5), achievement.getRequirements().get(0));
    }

    // Tests that unlockAchievement with a valid user returns true
    @Test
    public void testUnlockAchievementWithValidUserReturnsTrue() {
        Achievement achievement = new Achievement(new ArrayList<Integer>());

        boolean unlocked = achievement.unlockAchievement("jmiller");

        assertTrue(unlocked);
    }

    // Tests that unlockAchievement with null user returns false
    @Test
    public void testUnlockAchievementWithNullUserReturnsFalse() {
        Achievement achievement = new Achievement(new ArrayList<Integer>());

        boolean unlocked = achievement.unlockAchievement(null);

        assertFalse(unlocked);
    }

    // Tests that unlockAchievement with empty string user returns false
    @Test
    public void testUnlockAchievementWithEmptyUserReturnsFalse() {
        Achievement achievement = new Achievement(new ArrayList<Integer>());

        boolean unlocked = achievement.unlockAchievement("");

        assertFalse(unlocked);
    }

    // Tests that setting null requirements creates an empty list
    @Test
    public void testSetRequirementsWithNullCreatesEmptyList() {
        Achievement achievement = new Achievement(new ArrayList<Integer>());

        achievement.setRequirements(null);

        assertEquals(0, achievement.getRequirements().size());
    }
}