package com.example.scheduleapi.dtos;

import jakarta.validation.constraints.NotBlank;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleDto {
    @NotBlank
    private String title;
    @NotBlank
    private String note;
    private String dateschedule;
    private String timeschedule;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateschedule() {
        return dateschedule;
    }

    public void setDateschedule(String dateschedule) {
        this.dateschedule = dateschedule;
    }

    public String getTimeschedule() {
        return timeschedule;
    }

    public void setTimeschedule(String timeschedule) {
        this.timeschedule = timeschedule;
    }
}
