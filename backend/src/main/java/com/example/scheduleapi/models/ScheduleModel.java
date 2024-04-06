package com.example.scheduleapi.models;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "schedules")
public class ScheduleModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID schedule_id;
    @Column(nullable = false, length = 90)
    private String title;
    @Column(nullable = false, length = 90)
    private String note;
    @Column(nullable = false, length = 100)
    private String dateschedule;
    @Column(nullable = false, length = 100)
    private String timeschedule;

    public UUID getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(UUID schedule_id) {
        this.schedule_id = schedule_id;
    }

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
