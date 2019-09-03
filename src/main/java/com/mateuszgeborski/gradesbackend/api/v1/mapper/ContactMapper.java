package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.ContactDTO;
import com.mateuszgeborski.gradesbackend.domain.user.details.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO contactToContactDTO(Contact contact);

    Contact contactDTOtoContact(ContactDTO contactDTO);

}
