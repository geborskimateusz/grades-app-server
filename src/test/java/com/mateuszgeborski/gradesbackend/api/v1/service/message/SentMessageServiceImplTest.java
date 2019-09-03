package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.helpers.api.v1.factory.MessagesFactory;
import com.helpers.api.v1.service.ContainerAssertion;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.SentMessageRepository;
import com.mateuszgeborski.gradesbackend.domain.message.MessageContainer;
import com.mateuszgeborski.gradesbackend.domain.message.SentMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

import static com.helpers.api.v1.service.StudentServiceFactory.RANDOM_PAGE_VALUE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class SentMessageServiceImplTest {

    private long studentId = MessagesFactory.STUDENT_ID;

    private String messageContainer = MessageContainer.SENT.get();

    SentMessageService sentMessageService;

    @Mock
    SentMessageRepository sentMessageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        sentMessageService =
                new SentMessageServiceImpl(sentMessageRepository);
    }

    @Test
    void getMessagesByUserId() {

        Page<SentMessage> expected =
                MessagesFactory.getPageOfGivenType(messageContainer);


        when(sentMessageRepository
                .findSentMessagesByUserIdAndMessageContainer(anyLong(), anyString(), any(PageRequest.class)))
                .thenReturn(expected);

        MessageDTOs actual = sentMessageService.getMessagesByUserId(studentId, RANDOM_PAGE_VALUE);

        ContainerAssertion.assertMessagesOfGivenContainer(messageContainer, expected.getContent(), actual);
    }


    @Test
    void getReceivedMessagesByUserIdThrowsResourceNotFoundException() {

        when(sentMessageRepository
                .findSentMessagesByUserIdAndMessageContainer(anyLong(), anyString(), any(PageRequest.class)))
                .thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            sentMessageService.getMessagesByUserId(studentId, RANDOM_PAGE_VALUE);
        });
    }

    @Test
    void getMessageDTOById() {

        SentMessage expected = MessagesFactory.getFirstSentMessage();

        when(sentMessageRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        MessageDTO actual = sentMessageService.getMessageDTOById(studentId);

        assertAll(
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getDetails(), actual.getDetails()),
                () -> assertEquals(expected.getDateOfSending(), actual.getDateOfSending())
        );
    }

    @Test
    void getMessageDTOByIdThrowsResourceNotFoundException() {

        when(sentMessageRepository
                .findById(anyLong()))
                .thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            sentMessageService.getMessageDTOById(studentId);
        });
    }
}