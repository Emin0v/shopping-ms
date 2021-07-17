package com.company.service.impl;

import com.company.config.CustomerConfiguration;
import com.company.dto.CustomerCreateReqDto;
import com.company.dto.CustomerResDto;
import com.company.entity.Customer;
import com.company.exception.UserAlreadyExistException;
import com.company.exception.UserNotFoundException;
import com.company.repo.AddressRepository;
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
        if (customerRepository.findById(createReqDto.getId()).isPresent()) {
            throw new UserAlreadyExistException("User already exist");
        }

        Customer customer = new Customer(createReqDto);
        Customer customerDb = customerRepository.save(customer);

        return configuration.modelMapper().map(customerDb, CustomerResDto.class);

    }

    @Override
    @Transactional
    public CustomerResDto update(CustomerCreateReqDto createReqDto) {
        Customer customer = customerRepository.findById(createReqDto.getId())
                .orElseThrow(UserNotFoundException::new);

        Customer customerDb = customer.builder().id(createReqDto.getId()).name(createReqDto.getName())
                .surname(createReqDto.getSurname()).username(createReqDto.getUsername())
                .password(createReqDto.getPassword()).balance(createReqDto.getBalance())
                .build();

        return configuration.modelMapper().map(customerDb, CustomerResDto.class);
    }

    @Override
    public CustomerResDto getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return configuration.modelMapper().map(customer, CustomerResDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerResDto> searchNameOrSurnameOrUsername(String name, String surname, String username) {
         List<Customer> customerList =  customerRepository
                 .searchNameOrSurnameOrUsername(name,surname,username);

         return customerList.stream().map(customer->{
             return configuration.modelMapper().map(customer,CustomerResDto.class);
         }).collect(Collectors.toList());

    }
}
