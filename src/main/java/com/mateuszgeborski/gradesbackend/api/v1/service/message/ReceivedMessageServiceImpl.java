package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.MessageMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.ReceivedMessageRepository;
import com.mateuszgeborski.gradesbackend.domain.message.MessageContainer;
import com.mateuszgeborski.gradesbackend.domain.message.ReceivedMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceivedMessageServiceImpl extends AbstractMessageService implements ReceivedMessageService {

    private final MessageMapper messageMapper = MessageMapper.INSTANCE;

    private final ReceivedMessageRepository receivedMessageRepository;

    public ReceivedMessageServiceImpl(ReceivedMessageRepository receivedMessageRepository) {
        this.receivedMessageRepository = receivedMessageRepository;
    }

    @Override
    public MessageDTOs getMessagesByUserId(Long userId, Integer page) {


        Page<ReceivedMessage> receivedMessagesPage =
                receivedMessageRepository.
                        findReceivedMessagesByUserIdAndMessageContainer(
                                userId,
                                MessageContainer.RECEIVED.get(),
                                getPageableRequest(page)
                        );

        List<ReceivedMessage> receivedMessages = receivedMessagesPage.getContent();

        receivedMessages = convertReceivedToJsonProperty(receivedMessages);

        return convertFetchedMessagesToMessageDTOs(userId, receivedMessages);
    }

    @Override
    public MessageDTO getMessageDTOById(Long messageId) {
        Optional<ReceivedMessage> optionalReceivedMessage =
                this.receivedMessageRepository.findById(messageId);

        if (optionalReceivedMessage.isPresent()) {

            ReceivedMessage receivedMessage = optionalReceivedMessage.get();
            receivedMessage = changeReceivedUser(receivedMessage);

            return messageMapper.receivedMessageToMessageDTO(receivedMessage);
        } else {
            throw new ResourceNotFoundException("No messages found for user with id " + messageId);
        }
    }

    private List<ReceivedMessage> convertReceivedToJsonProperty(List<ReceivedMessage> receivedMessages) {
        return receivedMessages.stream().map(this::changeReceivedUser).collect(Collectors.toList());
    }

    private ReceivedMessage changeReceivedUser(ReceivedMessage receivedMessage) {
        receivedMessage.setOwner(receivedMessage.getSender());
        return receivedMessage;
    }

}
