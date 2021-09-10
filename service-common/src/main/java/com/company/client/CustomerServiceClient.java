package com.company.client;

import com.company.dto.customer.PayReqDto;
import com.company.dto.customer.RollbackReqDto;
import com.company.dto.customer.UserRespDto;
import com.company.dto.customer.RegisterReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("customer-service")
public interface CustomerServiceClient {

    @RequestMapping("/api/users/{uuid}")
    ResponseEntity<UserRespDto> getByUuid(@PathVariable("uuid") String uuid);

    @PostMapping("/api/users/register")
    void register(@RequestBody RegisterReqDto reqDto);


    @PostMapping("/api/payment/pay")
    void pay(@RequestBody PayReqDto reqDto);

    @PostMapping("/api/payment/rollback")
    void rollback(@RequestBody RollbackReqDto reqDto);

}
