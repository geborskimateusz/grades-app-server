package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.ContactMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.ContactDTO;
import com.mateuszgeborski.gradesbackend.api.v1.repository.ContactRepository;
import com.mateuszgeborski.gradesbackend.domain.user.details.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactMapper contactMapper = ContactMapper.INSTANCE;

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        Contact contact = contactMapper.contactDTOtoContact(contactDTO);
        Contact saved = contactRepository.save(contact);
        return contactMapper.contactToContactDTO(saved);
    }
}
