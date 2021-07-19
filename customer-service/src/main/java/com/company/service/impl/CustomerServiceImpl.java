package com.company.service.impl;

import com.company.client.contract.CustomerResDto;
import com.company.config.CustomerConfiguration;
import com.company.dto.CustomerCreateReqDto;
import com.company.entity.Customer;
import com.company.exception.UserAlreadyExistException;
import com.company.exception.UserNotFoundException;
import com.company.repo.CustomerRepository;
import com.company.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConfiguration configuration;

    @Override
    @Transactional
    public CustomerResDto create(CustomerCreateReqDto createReqDto) {
        Customer customer = new Customer(createReqDto);

        customerRepository.getByUuid(customer.getCustomerUuid())
                .orElseThrow(() -> new UserNotFoundException("User already exist: " + customer.getCustomerUuid()));

        Customer customerDb = customerRepository.save(customer);

        return configuration.modelMapper().map(customerDb, CustomerResDto.class);

    }

    @Override
    @Transactional
    public CustomerResDto update(String customerUuid, CustomerCreateReqDto createReqDto) {
        Customer customer = customerRepository.getByUuid(customerUuid)
                .orElseThrow(UserNotFoundException::new);

        Customer customerDb = customerRepository.save(
                configuration.modelMapper().map(createReqDto, customer.getClass()));

        return configuration.modelMapper().map(customerDb, CustomerResDto.class);
    }

    @Override
    public CustomerResDto getByUuid(String uuid) {
        Customer customer = customerRepository.getByUuid(uuid)
                .orElseThrow(UserNotFoundException::new);

        return configuration.modelMapper().map(customer, CustomerResDto.class);
    }

    @Override
    @Transactional
    public void delete(String uuid) {
        Customer customer = customerRepository.getByUuid(uuid)
                .orElseThrow(UserNotFoundException::new);
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerResDto> searchNameOrSurnameOrUsername(String name, String surname, String username) {
        List<Customer> customerList = customerRepository
                .searchNameOrSurnameOrUsername(name, surname, username);

        return customerList.stream().map(customer -> {
            return configuration.modelMapper().map(customer, CustomerResDto.class);
        }).collect(Collectors.toList());

    }
}
