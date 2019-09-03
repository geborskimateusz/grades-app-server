package com.mateuszgeborski.gradesbackend.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.ContactDTO;

public interface ContactService {
    ContactDTO save(ContactDTO contactDTO);
}
