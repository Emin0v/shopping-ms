package com.company.api;

import com.company.dto.AddressCreateReqDto;
import com.company.dto.AddressResDto;
import com.company.service.AddressService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(ApiPaths.AddressCtrl.CTRL)
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<AddressResDto> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AddressResDto> create(@RequestBody AddressCreateReqDto reqDto){
        return ResponseEntity.ok(addressService.create(reqDto));
    }


}
