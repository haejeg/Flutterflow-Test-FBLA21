package com.example.app;

public class Schedule {
    private String title; private String startTime; private String endTime; private String description;

    public Schedule (String title, String startTime, String endTime, String description) {
        title = this.title;
        startTime = this.startTime;
        endTime = this.endTime;
        description = this.description;
    }

    public String getTitle() {
        return title;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }
}
