package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface DeletedMessageController extends BaseMessageController {

    @ResponseStatus(HttpStatus.OK)
    void putMessageIntoDeleted(@PathVariable Long id, @PathVariable String msgType);

    @ResponseStatus(HttpStatus.OK)
    void deleteMessage(@PathVariable Long msgId);
}
