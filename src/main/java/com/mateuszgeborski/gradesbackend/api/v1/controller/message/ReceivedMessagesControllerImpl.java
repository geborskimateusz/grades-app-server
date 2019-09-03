package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.service.message.ReceivedMessageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "${cors.origin.value}")
@RestController
@RequestMapping(AbstractMessageController.API_V_1_MESSAGES)
public class ReceivedMessagesControllerImpl implements ReceivedMessageController {

    public static final String RECEIVED = "/received/";

    private final ReceivedMessageService receivedMessageService;

    public ReceivedMessagesControllerImpl(ReceivedMessageService receivedMessageService) {
        this.receivedMessageService = receivedMessageService;
    }

    @Override
    @GetMapping(value = RECEIVED + "{id}")
    public MessageDTOs getMessagesByUserId(Long id, Integer page) {
        System.out.println();
        System.out.println("Page = " + page);
        return receivedMessageService.getMessagesByUserId(id, page);
    }

    @Override
    @GetMapping(value = RECEIVED + "{messageId}/message")
    public MessageDTO getMessageById(Long messageId) {
        return receivedMessageService.getMessageDTOById(messageId);
    }
}
