package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.ResponseMessage;
import com.mateuszgeborski.gradesbackend.api.v1.service.message.PostMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "${cors.origin.value}")
@RestController
@RequestMapping(AbstractMessageController.API_V_1_MESSAGES)
public class PostMessageControllerImpl implements PostMessageController{

    private final PostMessageService postMessageService;

    public PostMessageControllerImpl(PostMessageService postMessageService) {
        this.postMessageService = postMessageService;
    }

    @Override
    @PostMapping(value = "/message")
    public ResponseMessage createMessage(@RequestBody ResponseMessage responseMessage) {
        return this.postMessageService.save(responseMessage);
    }
}
