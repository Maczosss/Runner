package com.example.runner;

public enum ActivityType {
    RUN("Run"),
    WALK("Walk");

    private String activity;

    ActivityType(String activity) {
        this.activity = activity;
    }

    public String getType(){
        return activity;
    }
}
