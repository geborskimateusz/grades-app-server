package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.AddressDTO;

public interface AddressService {
    AddressDTO save(AddressDTO addressDTO);
}
