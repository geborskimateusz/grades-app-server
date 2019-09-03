package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.AddressMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.AddressDTO;
import com.mateuszgeborski.gradesbackend.api.v1.repository.AddressRepository;
import com.mateuszgeborski.gradesbackend.domain.user.details.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressMapper addressMapper = AddressMapper.INSTANCE;

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        Address address = addressMapper.addressDTOtoAddress(addressDTO);
        Address saved = addressRepository.save(address);
        return addressMapper.addressToAddressDTO(saved);
    }
}
