package com.company.service.impl;

import com.company.dto.AddressCreateReqDto;
import com.company.dto.AddressResDto;
import com.company.entity.Address;
import com.company.repo.AddressRepository;
import com.company.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AddressResDto create(AddressCreateReqDto reqDto) {
        Address address = modelMapper.map(reqDto, Address.class);
        Address addressDb = addressRepository.save(address);

        return modelMapper.map(addressDb, AddressResDto.class);
    }

    @Override
    public AddressResDto getById(Long id) {
        return modelMapper.map(addressRepository.findById(id).get(), AddressResDto.class);
    }

    @Override
    @Transactional
    public AddressResDto update(AddressCreateReqDto reqDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        addressRepository.delete(addressRepository.findById(id).get());
    }
}
