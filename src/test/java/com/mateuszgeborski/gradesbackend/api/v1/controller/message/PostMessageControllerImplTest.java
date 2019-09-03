package com.mateuszgeborski.gradesbackend.api.v1.controller.message;

import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.ResponseMessage;
import com.mateuszgeborski.gradesbackend.api.v1.service.message.PostMessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.helpers.api.v1.controller.TestApiUrlStrings.API_V_1_MESSAGES_POST_MESSAGE;
import static com.helpers.api.v1.factory.AbstractRestControllerTest.asJsonString;
import static com.helpers.api.v1.factory.MessagesFactory.getResponseMessage;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostMessageControllerImplTest {


    @Mock
    PostMessageService postMessageService;

    PostMessageController postMessageController;

    MockMvc mockMvc;

    public PostMessageControllerImplTest() {
        MockitoAnnotations.initMocks(this);
        postMessageController = new PostMessageControllerImpl(postMessageService);
        mockMvc = MockMvcBuilders.standaloneSetup(postMessageController)
                .build();
    }


    @Test
    void createMessage() throws Exception {

        ResponseMessage responseMessage = getResponseMessage();

        when(postMessageService.save(any(ResponseMessage.class))).thenReturn(responseMessage);

        mockMvc.perform(post(API_V_1_MESSAGES_POST_MESSAGE)
                .content(asJsonString(responseMessage))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(postMessageService, times(1)).save(any(ResponseMessage.class));

    }
}