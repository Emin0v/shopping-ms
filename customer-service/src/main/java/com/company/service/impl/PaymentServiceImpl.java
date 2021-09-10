package com.company.service.impl;

import com.company.dto.CardDto;
import com.company.dto.customer.PayReqDto;
import com.company.dto.customer.RollbackReqDto;
import com.company.entity.Customer;
import com.company.exception.InsufficientFundException;
import com.company.exception.UserNotFoundException;
import com.company.repo.CustomerRepository;
import com.company.security.auth.service.SecurityService;
import com.company.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final CustomerRepository customerRepository;
    private final SecurityService securityService;

    @Override
    @Transactional
    public void pay(PayReqDto reqDto) {
        String currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);
        Customer customer = customerRepository.getByUserUuid(currentUserUuid).orElseThrow(UserNotFoundException::new);

        if(customer.getBalance().compareTo(reqDto.getTotalAmount())<0){
            throw new InsufficientFundException();
        }

        //payment service call...

        customerRepository.updateBalance(customer.getId(),customer.getBalance().subtract(reqDto.getTotalAmount()));

    }

    @Override
    @Transactional
    public void rollback(RollbackReqDto reqDto) {
        String currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);
        Customer customer = customerRepository.getByUserUuid(currentUserUuid).orElseThrow(UserNotFoundException::new);

        //payment service call...

        customerRepository.updateBalance(customer.getId(), customer.getBalance().add(reqDto.getTotalAmount()));
    }

    @Override
    @Transactional
    public void increaseBalance(CardDto dto) {
        if (dto.getDummyCardBalance().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientFundException();
        }

        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);
        var customer = customerRepository.getByUserUuid(currentUserUuid).orElseThrow(UserNotFoundException::new);

        //payment service call...

        customerRepository.updateBalance(customer.getId(), customer.getBalance().add(dto.getAmount()));
    }

}
