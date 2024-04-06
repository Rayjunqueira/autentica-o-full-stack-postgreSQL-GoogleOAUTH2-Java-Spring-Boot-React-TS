package com.example.scheduleapi.controllers;

import com.example.scheduleapi.dtos.ScheduleDto;
import com.example.scheduleapi.mappers.ScheduleMapper;
import com.example.scheduleapi.models.ScheduleModel;
import com.example.scheduleapi.services.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/schedule")
public class ScheduleController {
    final ScheduleService scheduleService;
    final ScheduleMapper scheduleMapper;

    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @PostMapping
    public ResponseEntity<Object> saveSchedule(@RequestBody @Valid ScheduleDto scheduleDto) {
        try {
            ScheduleModel scheduleModel = scheduleMapper.toScheduleModel(scheduleDto);
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.save(scheduleModel));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot create schedule. Check if the fields sent in your request are correct.");
        }
    }
    @GetMapping
    public ResponseEntity<List<ScheduleModel>> getSchedules() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneSchedule(@PathVariable(value = "id")UUID id) {
        try {
            Optional<ScheduleModel> scheduleModelOptional = scheduleService.findById(id);
            if (!scheduleModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Schedule not found!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(scheduleModelOptional.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The schedule wasn't found!");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSchedule (@PathVariable(value = "id") UUID id, @RequestBody @Valid ScheduleDto scheduleDto) {
        try {
            Optional<ScheduleModel> scheduleModelOptional = scheduleService.findById(id);
            if (!scheduleModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Schedule not found!");
            }
            ScheduleModel scheduleModel = scheduleModelOptional.get();
            scheduleModel.setTitle(scheduleDto.getTitle());
            scheduleModel.setNote(scheduleDto.getNote());
            scheduleModel.setDateschedule(scheduleDto.getDateschedule());
            scheduleModel.setTimeschedule(scheduleDto.getTimeschedule());

            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.save(scheduleModel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There was a issue! Check it again");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSchedule(@PathVariable(value = "id") UUID id) {
        try {
            Optional<ScheduleModel> scheduleModelOptional = scheduleService.findById(id);
            if (!scheduleModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Schedule not found!");
            }
            scheduleService.delete(scheduleModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Schedule was deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The schedule could not be found. Please check if the sent ID is correct.");
        }
    }
}
