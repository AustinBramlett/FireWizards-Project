package com.fwproblemsolversite.data;

import java.util.ArrayList;


public class Achievement {

    private String title;
    private String description;
    private ArrayList<Integer> requirements;

    // Constructor to initialize achievement with default values
    public Achievement(ArrayList<Integer> req) {
        this.requirements = (req == null) ? new ArrayList<>() : new ArrayList<>(req);
        this.title = "Achievement";
        this.description = "";  
    }

    // Method to check if the achievement is unlocked based on user progress
    public boolean unlockAchievement(String user) {
        return user != null && !user.trim().isEmpty();
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = (title == null) ? "" : title;
    }

    public String getDescription() {
        return description;
    }   

    public void setDescription(String description) {
        this.description = (description == null) ? "" : description;
    }

    public ArrayList<Integer> getRequirements() {
        return new ArrayList<>(requirements);
    }

    public void setRequirements(ArrayList<Integer> requirements) {
        this.requirements = (requirements == null) ? new ArrayList<>() : new ArrayList<>(requirements);
    }
}
