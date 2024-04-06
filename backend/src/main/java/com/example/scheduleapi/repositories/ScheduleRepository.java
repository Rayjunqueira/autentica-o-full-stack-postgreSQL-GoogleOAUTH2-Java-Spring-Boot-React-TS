package com.example.scheduleapi.repositories;

import com.example.scheduleapi.models.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, UUID> {
}
