package com.example.scheduleapi.mappers;

import com.example.scheduleapi.dtos.ScheduleDto;
import com.example.scheduleapi.models.ScheduleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(target = "schedule_id", ignore = true)
    ScheduleModel toScheduleModel(ScheduleDto scheduleDto);
}
