package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.EntityStringJoiner;
import com.helpers.api.v1.mapper.AddressMapperFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.*;
import com.mateuszgeborski.gradesbackend.domain.user.details.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    AddressMapper addressMapper = AddressMapper.INSTANCE;

    @Test
    void addressToAddressDTO() {
        Address expected = AddressMapperFactory.getAddress();

        AddressDTO actual = addressMapper.addressToAddressDTO(expected);

        assertEquals(
                EntityStringJoiner.mergeAddressDataToString(expected),
                actual.getCity() + "," +
                        actual.getUser().getFirstName() + "," +
                        actual.getUser().getContact().getEmail() + "," +
                        actual.getUser().getProfileImage().getImageUrl()

        );
    }

    @Test
    void addressDTOtoAddress() {
        AddressDTO expected = AddressMapperFactory.getAddressDTO();

        Address actual = addressMapper.addressDTOtoAddress(expected);

        assertEquals(
                expected.getCity() + "," +
                        expected.getUser().getFirstName() + "," +
                        expected.getUser().getContact().getEmail() + "," +
                        expected.getUser().getProfileImage().getImageUrl(),
                EntityStringJoiner.mergeAddressDataToString(actual)
        );
    }
}