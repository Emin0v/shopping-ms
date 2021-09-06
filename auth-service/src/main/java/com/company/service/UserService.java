package com.company.service;

import com.company.dto.customer.UserRespDto;
import com.company.dto.customer.UserUpdateDto;

import java.util.List;

public interface UserService {

    UserRespDto findByUserUuid(String uuid);

    void update(String customerUuid, UserUpdateDto dto);

    void delete(String uuid);

    List<UserRespDto> searchNameOrSurnameOrUsername(String name, String surname, String username);

}
