package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.service.message.DeletedMessageService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "${cors.origin.value}")
@RestController
@RequestMapping(AbstractMessageController.API_V_1_MESSAGES)
public class DeletedMessageControllerImpl implements DeletedMessageController {

    public static final String DELETED = "/deleted/";

    private final DeletedMessageService deletedMessageService;

    public DeletedMessageControllerImpl(DeletedMessageService deletedMessageService) {
        this.deletedMessageService = deletedMessageService;
    }

    @Override
    @PutMapping(value = DELETED + "temp/{id}/{msgType}")
    public void putMessageIntoDeleted(Long id, String msgType) {
        System.out.println("Putting to deleted message with ID = " + id + " which message container is " + msgType);
        deletedMessageService.putMessageToDeletedContainer(msgType, id);
    }

    @Override
    @DeleteMapping(value = DELETED + "{msgId}")
    public void deleteMessage(Long msgId) {
        deletedMessageService.deleteById(msgId);
    }

    @Override
    @GetMapping(value = DELETED + "{id}")
    public MessageDTOs getMessagesByUserId(Long id, Integer page) {
       return deletedMessageService.getMessagesByUserId(id, page);
    }

    @Override
    @GetMapping(value = DELETED + "{messageId}/message")
    public MessageDTO getMessageById(Long messageId) {
        return deletedMessageService.getMessageDTOById(messageId);
    }
}
