package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.ResponseMessage;

public interface PostMessageService {
    ResponseMessage save(ResponseMessage responseMessage);
}
