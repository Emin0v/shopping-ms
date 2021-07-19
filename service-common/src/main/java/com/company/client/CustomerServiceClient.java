package com.company.client;

import com.company.client.contract.CustomerResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("customer-service")
public interface CustomerServiceClient {

    @RequestMapping("/{id}")
    ResponseEntity<CustomerResDto> getByUuid(@PathVariable("id") String uuid);

}
