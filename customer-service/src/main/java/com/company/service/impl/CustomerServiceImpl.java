package com.company.service.impl;

import com.company.client.contract.CustomerResDto;
import com.company.dto.CustomerCreateReqDto;
import com.company.entity.Address;
import com.company.entity.Customer;
import com.company.exception.UserNotFoundException;
import com.company.repo.CustomerRepository;
import com.company.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CustomerResDto create(CustomerCreateReqDto createReqDto) {

        Customer customer = modelMapper.map(createReqDto, Customer.class);
        customer.setAddress(modelMapper.map(createReqDto.getAddressCreateReqDto(), Address.class));

        if (customerRepository.getByCustomerUuid(customer.getCustomerUuid()).isPresent()) {
            throw new UserNotFoundException("User already exist: " + customer.getCustomerUuid());
        }

        Customer customerDb = customerRepository.save(customer);

        return modelMapper.map(customerDb, CustomerResDto.class);

    }

    @Override
    @Transactional
    public CustomerResDto update(String customerUuid, CustomerCreateReqDto createReqDto) {
        Customer customer = customerRepository.getByCustomerUuid(customerUuid)
                .orElseThrow(UserNotFoundException::new);

        Customer customerDb = customerRepository.save(
                modelMapper.map(createReqDto, customer.getClass()));

        return modelMapper.map(customerDb, CustomerResDto.class);
    }

    @Override
    public CustomerResDto getByUuid(String uuid) {
        Customer customer = customerRepository.getByCustomerUuid(uuid)
                .orElseThrow(UserNotFoundException::new);

        return modelMapper.map(customer, CustomerResDto.class);
    }

    @Override
    @Transactional
    public void delete(String uuid) {
        Customer customer = customerRepository.getByCustomerUuid(uuid)
                .orElseThrow(UserNotFoundException::new);
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerResDto> searchNameOrSurnameOrUsername(String name, String surname, String username) {
        List<Customer> customerList = customerRepository
                .searchNameOrSurnameOrUsername(name, surname, username);

        return customerList.stream().map(customer -> {
            return modelMapper.map(customer, CustomerResDto.class);
        }).collect(Collectors.toList());

    }
}
