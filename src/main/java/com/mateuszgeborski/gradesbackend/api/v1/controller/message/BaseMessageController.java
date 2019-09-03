package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface BaseMessageController {

    @ResponseStatus(HttpStatus.OK)
    MessageDTOs getMessagesByUserId(
            @PathVariable Long id,
            @RequestParam(value = "page", defaultValue = "0") Integer page);

    @ResponseStatus(HttpStatus.OK)
    MessageDTO getMessageById(@PathVariable Long messageId);
}
