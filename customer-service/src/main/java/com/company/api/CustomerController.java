package com.company.api;

import com.company.dto.CustomerCreateReqDto;
import com.company.dto.CustomerResDto;
import com.company.service.CustomerService;
import com.company.util.ApiPaths;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping("/searchNameOrSurnameOrUsername")
    public ResponseEntity<List<CustomerResDto>>
    searchNameOrSurnameOrUsername(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String surname,
                                  @RequestParam(required = false) String username) {

        return ResponseEntity.ok(customerService.searchNameOrSurnameOrUsername(name, surname, username));
    }

    @PostMapping
    public ResponseEntity<CustomerResDto> create(@RequestBody CustomerCreateReqDto dto) {
        return ResponseEntity.ok(customerService.create(dto));
    }

    @PutMapping
    public ResponseEntity<CustomerResDto> update(@RequestBody CustomerCreateReqDto updateDto) {
        return ResponseEntity.ok(customerService.update(updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
