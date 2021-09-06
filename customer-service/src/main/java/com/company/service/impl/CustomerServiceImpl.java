package com.company.service.impl;

import com.company.client.CustomerServiceClient;
import com.company.entity.Customer;
import com.company.exception.UserAlreadyExistException;
import com.company.repo.CustomerRepository;
import com.company.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerServiceClient customerServiceClient;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void register(String uuid) {

        Optional<Customer> found = customerRepository.getByCustomerUuid(uuid);
        if(found.isPresent()){
            throw new UserAlreadyExistException(uuid);
        }

        customerRepository.save(Customer.builder().userUuid(uuid).build());

    }

}
