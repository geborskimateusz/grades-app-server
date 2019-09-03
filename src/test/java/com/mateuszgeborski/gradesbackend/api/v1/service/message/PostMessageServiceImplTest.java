package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.helpers.api.v1.factory.MessagesFactory;
import com.mateuszgeborski.gradesbackend.api.v1.mapper.MessageMapper;
import com.mateuszgeborski.gradesbackend.api.v1.mapper.UserMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.ResponseMessage;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.ReceivedMessageRepository;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.SentMessageRepository;
import com.mateuszgeborski.gradesbackend.api.v1.service.UserService;
import com.mateuszgeborski.gradesbackend.domain.message.ReceivedMessage;
import com.mateuszgeborski.gradesbackend.domain.message.SentMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class PostMessageServiceImplTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final MessageMapper messageMapper = MessageMapper.INSTANCE;

    PostMessageService postMessageService;

    @Mock
    private ReceivedMessageRepository receivedMessageRepository;

    @Mock
    private SentMessageRepository sentMessageRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        postMessageService =
                new PostMessageServiceImpl(receivedMessageRepository, sentMessageRepository, userService);
    }

    @Test
    void save() {
        ResponseMessage expected = MessagesFactory.getRequestMessage();

        UserDTO sender = MessagesFactory.getStudentDTO();
        sender.setId(MessagesFactory.SENDER_ID);

        UserDTO receiver = MessagesFactory.getStudentDTO();
        receiver.setId(MessagesFactory.RECEIVER_ID);

        SentMessage sentMessage =
                SentMessage.builder()
                        .details(expected.getDetails())
                        .title(expected.getTitle())
                        .owner(userMapper.userDTOtoUser(sender))
                        .receiver(userMapper.userDTOtoUser(receiver))
                        .build();

        ReceivedMessage receivedMessage =
                ReceivedMessage.builder()
                        .details(expected.getDetails())
                        .title(expected.getTitle())
                        .owner(userMapper.userDTOtoUser(receiver))
                        .sender(userMapper.userDTOtoUser(sender))
                        .build();

        when(userService.findById(anyLong())).thenReturn(sender);
        when(userService.findById(anyLong())).thenReturn(receiver);
        when(receivedMessageRepository.save(any(ReceivedMessage.class))).thenReturn(receivedMessage);
        when(sentMessageRepository.save(any(SentMessage.class))).thenReturn(sentMessage);

        ResponseMessage actual = postMessageService.save(expected);

        assertAll(
                () -> assertEquals(expected.getReceiverId(), actual.getReceiverId()),
                () -> assertEquals(expected.getSenderId(), actual.getSenderId())
        );
    }
}