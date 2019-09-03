package com.helpers.api.v1.service;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.domain.message.Message;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerAssertion {

    public static <T extends Message> void assertMessagesOfGivenContainer(String messageContainer, List<T> expected, MessageDTOs actual) {
        Message firstMessage = expected.get(0);
        MessageDTO firstMessageDTO = actual.getMessageDTOs().get(0);

        assertAll("check if found only " + messageContainer + " messages",
                () -> assertEquals(expected.size(), actual.getMessageDTOs().size())
        );
    }
}
