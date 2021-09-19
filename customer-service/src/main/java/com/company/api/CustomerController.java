package com.company.api;

import com.company.dto.customer.RegisterReqDto;
import com.company.service.CustomerService;
import com.company.util.ApiPaths;
import com.company.utilities.results.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin
@RequestMapping(ApiPaths.CustomerCtrl.CTRL)
@Api(value = ApiPaths.CustomerCtrl.CTRL, description = "Customer APIs")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Validated RegisterReqDto reqDto) {
        return customerService.register(reqDto.getUserUuid());
    }

    @GetMapping("/balance/{uuid}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable("uuid") String uuid){
        BigDecimal balance = customerService.getBalance(uuid);
        return ResponseEntity.ok(balance);
    }


}
