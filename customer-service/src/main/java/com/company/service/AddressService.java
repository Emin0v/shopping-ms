package com.company.service;

import com.company.dto.AddressCreateReqDto;
import com.company.dto.AddressResDto;

public interface AddressService {

    AddressResDto create(AddressCreateReqDto reqDto);

    AddressResDto getById(Long id);

    AddressResDto update(AddressCreateReqDto reqDto);

    void delete(Long id);

}
