package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import com.helpers.api.v1.factory.MessagesFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.service.message.ReceivedMessageService;
import com.mateuszgeborski.gradesbackend.domain.message.MessageContainer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.helpers.api.v1.controller.TestApiUrlStrings.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReceivedMessagesControllerImplTest {

    @Mock
    ReceivedMessageService receivedMessageService;

    ReceivedMessageController receivedMessageController;

    MockMvc mockMvc;

    public ReceivedMessagesControllerImplTest() {
        MockitoAnnotations.initMocks(this);
        receivedMessageController = new ReceivedMessagesControllerImpl(receivedMessageService);
        mockMvc = MockMvcBuilders.standaloneSetup(receivedMessageController)
                .build();
    }

    @Test
    void getMessagesByUserId() throws Exception {
        MessageDTOs expected = MessagesFactory
                .getMessageDTOsByType(MessageContainer.RECEIVED.get());

        System.out.println();
        System.out.println(expected.getMessageDTOs());
        System.out.println();
        MessageDTO firstMessageDTO = expected.getMessageDTOs().get(0);



        when(receivedMessageService.getMessagesByUserId(anyLong(), anyInt()))
                .thenReturn(expected);

        mockMvc.perform(get(API_V_1_MESSAGES_RECEIVED_5)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath($MESSAGES, hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath($FIRST_MESSAGE_TITLE,
                        equalTo(firstMessageDTO.getTitle())));

    }

    @Test
    void getMessageById() throws Exception {
        MessageDTO expected = MessagesFactory.getFirstReceivedMessageDTO();

        when(receivedMessageService.getMessageDTOById(anyLong()))
                .thenReturn(expected);

        mockMvc.perform(get(API_V_1_MESSAGES_RECEIVED_5_MESSAGE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath($MESSAGE_TITLE, equalTo(expected.getTitle())));

    }

}