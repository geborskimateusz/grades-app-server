package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.helpers.api.v1.mapper.ContactMapperFactory;
import com.helpers.api.v1.mapper.EntityStringJoiner;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.*;
import com.mateuszgeborski.gradesbackend.domain.user.details.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactMapperTest {

    ContactMapper contactMapper = ContactMapper.INSTANCE;

    @Test
    void contactDTOtoContact() {
        ContactDTO expected = ContactMapperFactory.getContactDTO();

        Contact actual = contactMapper.contactDTOtoContact(expected);

        assertEquals(
                expected.getEmail() + "," +
                        expected.getUser().getDateOfBirth().toString() + "," +
                        expected.getUser().getProfileImage().getImageUrl(),
                EntityStringJoiner.mergeContactToString(actual)

        );
    }

    @Test
    void contactToContactDTO() {
        Contact expected = ContactMapperFactory.getContact();

        ContactDTO actual = contactMapper.contactToContactDTO(expected);

        assertEquals(
                EntityStringJoiner.mergeContactToString(expected),
                actual.getEmail() + "," +
                        actual.getUser().getDateOfBirth().toString() + "," +
                        actual.getUser().getProfileImage().getImageUrl()

        );
    }

}