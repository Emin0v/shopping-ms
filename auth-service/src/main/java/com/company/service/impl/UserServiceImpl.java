package com.company.service.impl;

import com.company.client.CustomerServiceClient;
import com.company.dto.customer.UserRespDto;
import com.company.dto.customer.UserUpdateDto;
import com.company.entity.User;
import com.company.repo.UserRepository;
import com.company.service.UserService;
import com.company.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CustomerServiceClient customerServiceClient;

    @Override
    public UserRespDto findByUserUuid(String uuid) {
        User user = userRepository.findByUuid(uuid).get();

        UserRespDto userRespDto = userMapper.toUserRespDto(user);
        userRespDto.setBalance(customerServiceClient.getBalance(user.getUuid()).getBody());

        return userRespDto;
    }

    @Override
    @Transactional
    public void update(String userUuid, UserUpdateDto dto) {
        User user = userRepository.findByUuid(userUuid).get();
        user.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .build();

    }

    @Override
    @Transactional
    public void delete(String uuid) {
        userRepository.delete(
                userRepository.findByUuid(uuid).get()
        );
    }

    @Override
    public List<UserRespDto> searchNameOrSurnameOrUsername(String name, String surname, String username) {
        List<User> userList = userRepository
                .searchNameOrSurnameOrUsername(name, surname, username);

        return userList.stream().map(userMapper::toUserRespDto).collect(Collectors.toList());

    }
}
