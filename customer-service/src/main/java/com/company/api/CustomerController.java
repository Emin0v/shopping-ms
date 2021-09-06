package com.company.api;

import com.company.dto.customer.RegisterReqDto;
import com.company.service.CustomerService;
import com.company.util.ApiPaths;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> register(@RequestBody @Validated RegisterReqDto reqDto) {
        customerService.register(reqDto.getUserUuid());
        return ResponseEntity.status(CREATED).build();
    }


}
