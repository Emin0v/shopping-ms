package com.company.service.mapper;

import com.company.dto.RegisterRequestDto;
import com.company.dto.customer.UserRespDto;
import com.company.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "authority", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    User registerDtoToEntity(RegisterRequestDto registerRequestDto);

    UserRespDto toUserRespDto(User user);

}
