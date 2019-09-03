package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.MessageMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.DeletedMessageRepository;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.ReceivedMessageRepository;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.SentMessageRepository;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import com.mateuszgeborski.gradesbackend.domain.message.*;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeletedMessageServiceImpl extends AbstractMessageService implements DeletedMessageService {

    private final MessageMapper messageMapper = MessageMapper.INSTANCE;

    private final DeletedMessageRepository deletedMessageRepository;

    private final ReceivedMessageRepository receivedMessageRepository;

    private final SentMessageRepository sentMessageRepository;

    public DeletedMessageServiceImpl(DeletedMessageRepository deletedMessageRepository, ReceivedMessageRepository receivedMessageRepository, SentMessageRepository sentMessageRepository) {
        this.deletedMessageRepository = deletedMessageRepository;
        this.receivedMessageRepository = receivedMessageRepository;
        this.sentMessageRepository = sentMessageRepository;
    }

    @Override
    public MessageDTOs getMessagesByUserId(Long userId, Integer page) {
        Page<DeletedMessage> deletedMessagePage =
                deletedMessageRepository.
                        findDeletedMessagesByUserIdAndMessageContainer(
                                userId, MessageContainer.DELETED.get(),
                                getPageableRequest(page)
                        );

        List<DeletedMessage> deletedMessages = deletedMessagePage.getContent();

        deletedMessages = convertDeletedToJsonProperty(deletedMessages);

        return convertFetchedMessagesToMessageDTOs(userId, deletedMessages);
    }

    @Override
    public MessageDTO getMessageDTOById(Long messageId) {
        Optional<DeletedMessage> optionalDeletedMessage =
                this.deletedMessageRepository.findById(messageId);

        if (optionalDeletedMessage.isPresent()) {
            DeletedMessage deletedMessage = optionalDeletedMessage.get();
            deletedMessage = changeDeletedUser(deletedMessage);
            return messageMapper.deletedMessageToMessageDTO(deletedMessage);
        } else {
            throw new ResourceNotFoundException("No messages found for user with id " + messageId);
        }
    }

    @Override
    public void putMessageToDeletedContainer(String messageType, Long messageId) {
        if (isReceivedMessage(messageType)) {
            putReceivedIfPresent(messageId);
        } else {
            putSentIfPresent(messageId);
        }
    }

    @Override
    public void deleteById(Long msgId) {
        deletedMessageRepository.deleteById(msgId);
    }

    private void putSentIfPresent(Long messageId) {
        Optional<SentMessage> optionalSentMessage =
                sentMessageRepository.findById(messageId);
        if (optionalSentMessage.isPresent()) {
            SentMessage message = optionalSentMessage.get();
            changeMessageContainer(message, message.getReceiver());
        } else {
            throw new ResourceNotFoundException("No messages found for user with id " + messageId);
        }
    }

    private void putReceivedIfPresent(Long messageId) {
        Optional<ReceivedMessage> optionalReceivedMessage =
                receivedMessageRepository.findById(messageId);
        if (optionalReceivedMessage.isPresent()) {
            ReceivedMessage message = optionalReceivedMessage.get();
            changeMessageContainer(message, message.getSender());
        } else {
            throw new ResourceNotFoundException("No messages found for user with id " + messageId);
        }
    }

    private <T extends Message> void changeMessageContainer(T message, User senderOrReceiver) {

        DeletedMessage deletedMessage =
                DeletedMessage.builder()
                        .title(message.getTitle())
                        .details(message.getDetails())
                        .owner(message.getOwner())
                        .senderOrReceiver(senderOrReceiver)
                        .build();

        if (isReceivedMessage(message.getMessageContainer())) {
            receivedMessageRepository.delete(
                    messageMapper.messageToReceivedMessage(message));
        } else {
            sentMessageRepository.delete(
                    messageMapper.messageToSentMessage(message));
        }

        deletedMessageRepository.save(deletedMessage);
    }

    private boolean isReceivedMessage(String messageType) {
        return messageType.equals(MessageContainer.RECEIVED.get());
    }

    private List<DeletedMessage> convertDeletedToJsonProperty(List<DeletedMessage> deletedMessages) {
        return deletedMessages.stream().map(this::changeDeletedUser).collect(Collectors.toList());
    }

    private DeletedMessage changeDeletedUser(DeletedMessage deletedMessage) {
        deletedMessage.setOwner(deletedMessage.getSenderOrReceiver());
        return deletedMessage;
    }

}
