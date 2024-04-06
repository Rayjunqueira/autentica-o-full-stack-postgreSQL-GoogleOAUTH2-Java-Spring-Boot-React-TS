package com.example.scheduleapi.mappers;

import com.example.scheduleapi.dtos.UserDto;
import com.example.scheduleapi.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "user_id", ignore = true)
    UserModel toUserModel(UserDto userDto);
}
