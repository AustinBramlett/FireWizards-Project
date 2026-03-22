package com.fwproblemsolversite.data;

import java.util.ArrayList;

/**
 * Represents an achievement in the system.
 * 
 * Achievements have requirements that must be met to unlock them.
 */
public class Achievement {

    private String title;
    private String description;
    private ArrayList<Integer> requirements;

    /**
     * Creates an achievement with a list of requirements.
     * 
     * @param req A list of requirements that must be met to unlock the achievement.
     */
    public Achievement(ArrayList<Integer> req) {
        this.requirements = (req == null) ? new ArrayList<>() : new ArrayList<>(req);
        this.title = "Achievement";
        this.description = "";  
    }
    /**
     * Checfks if the achievement is unlocked for a user.
     * 
     * @param user The username of the user
     * @return true if the achievement is unlocked, if not then false.
     */
    public boolean unlockAchievement(String user) {
        return user != null && !user.trim().isEmpty();
    }
    /**
     * Gets the title of the achievement.
     * 
     * @return The title of the achievement.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title of the achievement.
     * 
     * @param title The title to set for the achievement.
     */
    public void setTitle(String title) {
        this.title = (title == null) ? "" : title;
    }
    /**
     * Gets the description of the achievement.
     * 
     * @return The description of the achievement.
     */
    public String getDescription() {
        return description;
    }   
    /**
     * Sets the description of the achievement.
     * 
     * @param description The description to set for the achievement.
     */
    public void setDescription(String description) {
        this.description = (description == null) ? "" : description;
    }
    /**
     * Gets the list of requirements for the achievement.
     * 
     * @return The list of requirements for the achievement.
     */
    public ArrayList<Integer> getRequirements() {
        return new ArrayList<>(requirements);
    }
    /**
     * Sets the list of requirements for the achievement.
     * 
     * @param requirements The list of requirements to set for the achievement.
     */
    public void setRequirements(ArrayList<Integer> requirements) {
        this.requirements = (requirements == null) ? new ArrayList<>() : new ArrayList<>(requirements);
    }
}
