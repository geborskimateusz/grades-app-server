package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.service.message.SentMessageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "${cors.origin.value}")
@RestController
@RequestMapping(AbstractMessageController.API_V_1_MESSAGES)
public class SentMessageControllerImpl implements SentMessageController {

    public static final String SENT = "/sent/";

    private final SentMessageService sentMessageService;

    public SentMessageControllerImpl(SentMessageService sentMessageService) {
        this.sentMessageService = sentMessageService;
    }

    @Override
    @GetMapping(value = SENT + "{id}")
    public MessageDTOs getMessagesByUserId(Long id, Integer page) {
        return sentMessageService.getMessagesByUserId(id, page);
    }

    @Override
    @GetMapping(value = SENT + "{messageId}/message")
    public MessageDTO getMessageById(Long messageId) {
        return sentMessageService.getMessageDTOById(messageId);
    }
}
