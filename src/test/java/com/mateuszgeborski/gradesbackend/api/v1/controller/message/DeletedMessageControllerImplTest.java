package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import com.helpers.api.v1.factory.MessagesFactory;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.service.message.DeletedMessageService;
import com.mateuszgeborski.gradesbackend.domain.message.MessageContainer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.helpers.api.v1.controller.TestApiUrlStrings.*;
import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

class DeletedMessageControllerImplTest {

    @Mock
    DeletedMessageService deletedMessageService;

    DeletedMessageController deletedMessageController;

    MockMvc mockMvc;

    public DeletedMessageControllerImplTest() {
        MockitoAnnotations.initMocks(this);
        deletedMessageController = new DeletedMessageControllerImpl(deletedMessageService);
        mockMvc = MockMvcBuilders.standaloneSetup(deletedMessageController)
                .build();
    }


    @Test
    void putMessageIntoDeleted() throws Exception {
        mockMvc.perform(put(API_V_1_MESSAGES_DELETED_5_TEMP)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMessage() throws Exception {
        mockMvc.perform(delete(API_V_1_MESSAGES_DELETED_5)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(deletedMessageService, times(1)).deleteById(anyLong());
    }

    @Test
    void getMessagesByUserId() throws Exception {

        MessageDTOs expected = MessagesFactory
                .getMessageDTOsByType(MessageContainer.DELETED.get());

        MessageDTO firstMessageDTO = expected.getMessageDTOs().get(0);

        when(deletedMessageService.getMessagesByUserId(anyLong(), anyInt()))
                .thenReturn(expected);

        mockMvc.perform(get(API_V_1_MESSAGES_DELETED_5)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath($MESSAGES, hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath($FIRST_MESSAGE_TITLE,
                        equalTo(firstMessageDTO.getTitle())));
    }

    @Test
    void getDeletedMessagesByUserIdNotFound() throws Exception {

        when(deletedMessageController.getMessagesByUserId(anyLong(), anyInt())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(
                get(API_V_1_MESSAGES_DELETED_5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getMessageById() throws Exception {
        MessageDTO expected = MessagesFactory.getFirstDeletedMessageDTO();

        when(deletedMessageService.getMessageDTOById(anyLong()))
                .thenReturn(expected);

        mockMvc.perform(get(API_V_1_MESSAGES_DELETED_5_MESSAGE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath($MESSAGE_TITLE, equalTo(expected.getTitle())));


    }
}