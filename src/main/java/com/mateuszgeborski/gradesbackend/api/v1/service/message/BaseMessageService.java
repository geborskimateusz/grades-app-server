package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;

public interface BaseMessageService {

    MessageDTOs getMessagesByUserId(Long userId, Integer page);

    MessageDTO getMessageDTOById(Long messageId);

}
