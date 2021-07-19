package com.company.api;

import com.company.client.contract.CustomerResDto;
import com.company.dto.CustomerCreateReqDto;
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
    public ResponseEntity<CustomerResDto> getByUuid(@PathVariable("id") String uuid) {
        return ResponseEntity.ok(customerService.getByUuid(uuid));
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

    @PutMapping("/{uuid}")
    public ResponseEntity<CustomerResDto> update(@PathVariable(name = "uuid") String uuid,
                                                     @RequestBody CustomerCreateReqDto updateDto) {
        return ResponseEntity.ok(customerService.update(uuid,updateDto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("uuid") String uuid) {
        customerService.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
