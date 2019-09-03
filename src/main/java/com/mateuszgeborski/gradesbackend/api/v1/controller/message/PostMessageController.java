package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface PostMessageController {

    @ResponseStatus(HttpStatus.CREATED)
    ResponseMessage createMessage(@RequestBody ResponseMessage responseMessage);
}
