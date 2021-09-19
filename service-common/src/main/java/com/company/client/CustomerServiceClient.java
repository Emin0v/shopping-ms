package com.company.client;

import com.company.dto.customer.PayReqDto;
import com.company.dto.customer.RollbackReqDto;
import com.company.dto.customer.UserRespDto;
import com.company.dto.customer.RegisterReqDto;
import com.company.utilities.results.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient("customer-service")
public interface CustomerServiceClient {

    @RequestMapping("/api/users/{uuid}")
    ResponseEntity<UserRespDto> getByUuid(@PathVariable("uuid") String uuid);

    @GetMapping("api/users/balance/{uuid}")
    ResponseEntity<BigDecimal> getBalance(@PathVariable("uuid") String uuid);

    @PostMapping("/api/users/register")
    Result register(@RequestBody RegisterReqDto reqDto);

    @PostMapping("/api/payment/pay")
    void pay(@RequestBody PayReqDto reqDto);

    @PostMapping("/api/payment/rollback")
    void rollback(@RequestBody RollbackReqDto reqDto);


}
