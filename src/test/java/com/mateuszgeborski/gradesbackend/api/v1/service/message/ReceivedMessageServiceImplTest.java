package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.helpers.api.v1.factory.MessagesFactory;
import com.helpers.api.v1.service.ContainerAssertion;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.ReceivedMessageRepository;
import com.mateuszgeborski.gradesbackend.domain.message.MessageContainer;
import com.mateuszgeborski.gradesbackend.domain.message.ReceivedMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

import static com.helpers.api.v1.service.StudentServiceFactory.RANDOM_PAGE_VALUE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ReceivedMessageServiceImplTest {

    private long studentId = MessagesFactory.STUDENT_ID;

    private String messageContainer = MessageContainer.RECEIVED.get();

    ReceivedMessageService receivedMessageService;

    @Mock
    ReceivedMessageRepository receivedMessageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        receivedMessageService =
                new ReceivedMessageServiceImpl(receivedMessageRepository);
    }

    @Test
    void getMessagesByUserId() {

        Page<ReceivedMessage> expected =
                MessagesFactory.getPageOfGivenType(messageContainer);


        when(receivedMessageRepository
                .findReceivedMessagesByUserIdAndMessageContainer(anyLong(), anyString(), any(PageRequest.class)))
                .thenReturn(expected);

        MessageDTOs actual = receivedMessageService.getMessagesByUserId(studentId, RANDOM_PAGE_VALUE);

        ContainerAssertion.assertMessagesOfGivenContainer(messageContainer, expected.getContent(), actual);
    }

    @Test
    void getReceivedMessagesByUserIdThrowsResourceNotFoundException() {

        when(receivedMessageRepository
                .findReceivedMessagesByUserIdAndMessageContainer(anyLong(), anyString(), any(PageRequest.class)))
                .thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            receivedMessageService.getMessagesByUserId(studentId, RANDOM_PAGE_VALUE);
        });
    }

    @Test
    void getMessageDTOById() {

        ReceivedMessage expected = MessagesFactory.getFirstReceivedMessage();

        when(receivedMessageRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        MessageDTO actual = receivedMessageService.getMessageDTOById(studentId);

        assertAll(
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getDetails(), actual.getDetails()),
                () -> assertEquals(expected.getDateOfSending(), actual.getDateOfSending())
        );
    }

    @Test
    void getMessageDTOByIdThrowsResourceNotFoundException() {

        when(receivedMessageRepository
                .findById(anyLong()))
                .thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            receivedMessageService.getMessageDTOById(studentId);
        });
    }
}
