package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.helpers.api.v1.factory.MessagesFactory;
import com.helpers.api.v1.service.ContainerAssertion;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.DeletedMessageRepository;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.ReceivedMessageRepository;
import com.mateuszgeborski.gradesbackend.api.v1.repository.message.SentMessageRepository;
import com.mateuszgeborski.gradesbackend.domain.message.DeletedMessage;
import com.mateuszgeborski.gradesbackend.domain.message.MessageContainer;
import com.mateuszgeborski.gradesbackend.domain.message.ReceivedMessage;
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
import static org.mockito.Mockito.*;

class DeletedMessageServiceImplTest {

    private long studentId = MessagesFactory.STUDENT_ID;

    private String messageContainer = MessageContainer.DELETED.get();

    DeletedMessageService deletedMessageService;

    @Mock
    DeletedMessageRepository deletedMessageRepository;

    @Mock
    ReceivedMessageRepository receivedMessageRepository;

    @Mock
    SentMessageRepository sentMessageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        deletedMessageService =
                new DeletedMessageServiceImpl(deletedMessageRepository,receivedMessageRepository,sentMessageRepository);
    }


    @Test
    void getMessagesByUserId() {

        Page<DeletedMessage> expected =
                MessagesFactory.getPageOfGivenType(messageContainer);


        when(deletedMessageRepository
                .findDeletedMessagesByUserIdAndMessageContainer(anyLong(), anyString(), any(PageRequest.class)))
                .thenReturn(expected);

        MessageDTOs actual = deletedMessageService.getMessagesByUserId(studentId, RANDOM_PAGE_VALUE );

        ContainerAssertion.assertMessagesOfGivenContainer(messageContainer, expected.getContent(), actual);
    }

    @Test
    void getReceivedMessagesByUserIdThrowsResourceNotFoundException() {

        when(deletedMessageRepository
                .findDeletedMessagesByUserIdAndMessageContainer(anyLong(), anyString(), any(PageRequest.class)))
                .thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            deletedMessageService.getMessagesByUserId(studentId, RANDOM_PAGE_VALUE);
        });
    }

    @Test
    void getMessageDTOById() {

        DeletedMessage expected = MessagesFactory.getDeletedMessage();

        when(deletedMessageRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        MessageDTO actual = deletedMessageService.getMessageDTOById(studentId);

        assertAll(
                () -> assertEquals(expected.getTitle(), actual.getTitle()),
                () -> assertEquals(expected.getDetails(), actual.getDetails()),
                () -> assertEquals(expected.getDateOfSending(), actual.getDateOfSending())
        );
    }

    @Test
    void getMessageDTOByIdThrowsResourceNotFoundException() {

        when(deletedMessageRepository
                .findById(anyLong()))
                .thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> {
            deletedMessageService.getMessageDTOById(studentId);
        });
    }

    @Test
    void putReceivedMessageToDeletedContainer() {
        String messageType = MessageContainer.RECEIVED.get();

        ReceivedMessage expected = MessagesFactory.getFirstReceivedMessage();

        when(receivedMessageRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        deletedMessageService.putMessageToDeletedContainer(messageType, studentId);

        verify(receivedMessageRepository, times(1)).delete(any(ReceivedMessage.class));
        verify(sentMessageRepository, never()).delete(any(SentMessage.class));
        verify(deletedMessageRepository, times(1)).save(any(DeletedMessage.class));
    }

}