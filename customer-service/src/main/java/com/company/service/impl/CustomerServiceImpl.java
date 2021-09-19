package com.company.service.impl;

import com.company.client.CartServiceClient;
import com.company.dto.customer.UserRespDto;
import com.company.entity.Customer;
import com.company.repo.CustomerRepository;
import com.company.service.CustomerService;
import com.company.utilities.results.DataResult;
import com.company.utilities.results.ErrorDataResult;
import com.company.utilities.results.ErrorResult;
import com.company.utilities.results.Result;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CartServiceClient cartServiceClient;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Result register(String uuid) {

        Optional<Customer> found = customerRepository.getByUserUuid(uuid);
        if(found.isPresent()){
             return new Result(false,"User already exist");
//            throw new UserAlreadyExistException(uuid);
        }

        Customer saved = customerRepository.save(Customer.builder().userUuid(uuid).build());
        cartServiceClient.createCart(saved.getCartId());

        return new Result(true,"User registered");
    }

    @Override
    public BigDecimal getBalance(String userUuid){
        return customerRepository.getBalance(userUuid);
    }

}
