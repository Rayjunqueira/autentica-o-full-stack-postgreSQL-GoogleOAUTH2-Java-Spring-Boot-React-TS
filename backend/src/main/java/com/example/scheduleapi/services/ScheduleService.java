package com.example.scheduleapi.services;

import com.example.scheduleapi.models.ScheduleModel;
import com.example.scheduleapi.repositories.ScheduleRepository;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleService {
    final ScheduleRepository scheduleRepository;
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    public ScheduleModel save (ScheduleModel scheduleModel) {
        return scheduleRepository.save(scheduleModel);
    }

    public List<ScheduleModel> findAll() {
        return scheduleRepository.findAll();
    }
    public Optional<ScheduleModel> findById(UUID id) {
        return scheduleRepository.findById(id);
    }
    public void delete(ScheduleModel scheduleModel) {
        scheduleRepository.delete(scheduleModel);
    }
}
