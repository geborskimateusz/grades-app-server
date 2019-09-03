package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.MessageMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.SentMessageRepository;
import com.mateuszgeborski.gradesbackend.domain.message.MessageContainer;
import com.mateuszgeborski.gradesbackend.domain.message.SentMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SentMessageServiceImpl extends AbstractMessageService implements SentMessageService{

    private final MessageMapper messageMapper = MessageMapper.INSTANCE;

    private final SentMessageRepository sentMessageRepository;

    public SentMessageServiceImpl(SentMessageRepository sentMessageRepository) {
        this.sentMessageRepository = sentMessageRepository;
    }

    @Override
    public MessageDTOs getMessagesByUserId(Long userId, Integer page) {

        Page<SentMessage> sentMessagePage =
                sentMessageRepository.
                        findSentMessagesByUserIdAndMessageContainer(
                                userId,
                                MessageContainer.SENT.get(),
                                getPageableRequest(page)
                        );
        List<SentMessage> sentMessages = sentMessagePage.getContent();

        sentMessages = convertSentToJsonProperty(sentMessages);

        return convertFetchedMessagesToMessageDTOs(userId, sentMessages);
    }

    @Override
    public MessageDTO getMessageDTOById(Long messageId) {
        Optional<SentMessage> optionalSentMessage =
                this.sentMessageRepository.findById(messageId);

        if (optionalSentMessage.isPresent()) {
            SentMessage sentMessage = optionalSentMessage.get();
            sentMessage = changeSentUser(sentMessage);
            return messageMapper.sentMessageToMessageDTO(sentMessage);
        } else {
            throw new ResourceNotFoundException("No messages found for user with id " + messageId);
        }
    }

    private List<SentMessage> convertSentToJsonProperty(List<SentMessage> receivedMessages) {
        return receivedMessages.stream().map(this::changeSentUser).collect(Collectors.toList());
    }

    private SentMessage changeSentUser(SentMessage sentMessage) {
        sentMessage.setOwner(sentMessage.getReceiver());
        return sentMessage;
    }
}
