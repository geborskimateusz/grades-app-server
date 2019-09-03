package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.MessageMapper;
import com.mateuszgeborski.gradesbackend.api.v1.mapper.UserMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.ReceivedMessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.SentMessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.ResponseMessage;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.ReceivedMessageRepository;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.SentMessageRepository;
import com.mateuszgeborski.gradesbackend.api.v1.service.UserService;
import com.mateuszgeborski.gradesbackend.domain.message.ReceivedMessage;
import com.mateuszgeborski.gradesbackend.domain.message.SentMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostMessageServiceImpl implements PostMessageService {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final MessageMapper messageMapper = MessageMapper.INSTANCE;

    private final ReceivedMessageRepository receivedMessageRepository;

    private final SentMessageRepository sentMessageRepository;

    private final UserService userService;

    public PostMessageServiceImpl(ReceivedMessageRepository receivedMessageRepository, SentMessageRepository sentMessageRepository, UserService userService) {
        this.receivedMessageRepository = receivedMessageRepository;
        this.sentMessageRepository = sentMessageRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public ResponseMessage save(ResponseMessage responseMessage) {
        SentMessageDTO savedSent = setSentMessage(responseMessage);
        ReceivedMessageDTO savedReceived = setReceivedMessage(responseMessage);

        return ResponseMessage.builder()
                .title(responseMessage.getTitle())
                .details(responseMessage.getDetails())
                .receiverId(savedSent.getReceiver().getId())
                .senderId(savedReceived.getSender().getId())
                .build();
    }

    private ReceivedMessageDTO setReceivedMessage(ResponseMessage responseMessage) {
        System.out.println("setReceivedMessage");
        UserDTO owner = userService.findById(responseMessage.getReceiverId());
        UserDTO sender = userService.findById(responseMessage.getSenderId());

        ReceivedMessage receivedMessage =
                ReceivedMessage.builder()
                        .details(responseMessage.getDetails())
                        .title(responseMessage.getTitle())
                        .owner(userMapper.userDTOtoUser(owner))
                        .sender(userMapper.userDTOtoUser(sender))
                        .build();
        return messageMapper.receivedMessageToReceivedMessageDTO(receivedMessageRepository.save(receivedMessage));
    }

    private SentMessageDTO setSentMessage(ResponseMessage responseMessage) {
        System.out.println("setSentMessage");
        UserDTO owner = userService.findById(responseMessage.getSenderId());
        UserDTO receiver = userService.findById(responseMessage.getReceiverId());


        SentMessage sentMessage =
                SentMessage.builder()
                        .details(responseMessage.getDetails())
                        .title(responseMessage.getTitle())
                        .owner(userMapper.userDTOtoUser(owner))
                        .receiver(userMapper.userDTOtoUser(receiver))
                        .build();
        return messageMapper.sentMessageToSentMessageDTO(sentMessageRepository.save(sentMessage));
    }
}
