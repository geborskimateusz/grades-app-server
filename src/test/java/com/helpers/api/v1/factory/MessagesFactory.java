package com.helpers.api.v1.factory;

import com.mateuszgeborski.gradesbackend.api.v1.mapper.MessageMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.user.UserDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.DeletedMessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.ReceivedMessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.SentMessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.ResponseMessage;
import com.mateuszgeborski.gradesbackend.domain.user.User;
import com.mateuszgeborski.gradesbackend.domain.message.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessagesFactory {
    public static final Long STUDENT_ID = 5L;
    private static final String MESSAGE_TITLE = "MESSAGES_DATA_SOURCE";
    private static final String MESSAGE_DETAILS = "fake message details";
    public static final Long RECEIVER_ID = 1L;
    public static final Long SENDER_ID = 5L;
    private static final String STUDENT_NAME = "studentName";
    private static final String STUDENT_LAST_NAME = "studentLastName";


    private static MessageMapper messageMapper = MessageMapper.INSTANCE;

    public static<T> Page getPageOfGivenType(String messageContainer) {
        Page page = new PageImpl<T>(getMessageByType(messageContainer));
        System.out.println(page.getContent());
        return page;
    }

    private static <T extends Message> List getMessageByType(String messageContainer) {
        if (messageContainer.equals(MessageContainer.RECEIVED.get())) {
            return getReceivedMessages();
        } else if (messageContainer.equals(MessageContainer.SENT.get())) {
            return getSentMessages();
        } else {
            return getDeletedMessages();
        }
    }

    public static MessageDTOs getMessageDTOsByType(String messageContainer) {
        if (messageContainer.equals(MessageContainer.RECEIVED.get())) {
            return MessageDTOs.builder().messageDTOs(getReceivedMessageDTOs()).build();
        } else if (messageContainer.equals(MessageContainer.SENT.get())) {
            return MessageDTOs.builder().messageDTOs(getSentMessageDTOs()).build();
        } else {
            return MessageDTOs.builder().messageDTOs(getDeletedMessageDTOs()).build();
        }
    }

    private static List<ReceivedMessage> getReceivedMessages() {
        return Stream.of(
                ReceivedMessage.builder()
                        .title(MESSAGE_TITLE)
                        .details(MESSAGE_DETAILS)
                        .owner(getStudent())
                        .sender(getStudent())
                        .build(),
                ReceivedMessage.builder()
                        .title(MESSAGE_TITLE)
                        .details(MESSAGE_DETAILS)
                        .owner(getStudent())
                        .sender(getStudent())
                        .build()
        ).collect(Collectors.toList());
    }

    private static List<ReceivedMessageDTO> getReceivedMessageDTOs() {
        return getReceivedMessages()
                .stream()
                .map(receivedMessage -> messageMapper.receivedMessageToReceivedMessageDTO(receivedMessage))
                .collect(Collectors.toList());
    }

    private static List<SentMessage> getSentMessages() {
        return Stream.of(
                SentMessage.builder()
                        .title(MESSAGE_TITLE)
                        .details(MESSAGE_DETAILS)
                        .owner(getStudent())
                        .receiver(getStudent())
                        .build(),
                SentMessage.builder()
                        .title(MESSAGE_TITLE)
                        .details(MESSAGE_DETAILS)
                        .owner(getStudent())
                        .receiver(getStudent())
                        .build()
        ).collect(Collectors.toList());
    }

    private static List<SentMessageDTO> getSentMessageDTOs() {
        return getSentMessages()
                .stream()
                .map(sentMessage -> messageMapper.sentMessageToSentMessageDTO(sentMessage))
                .collect(Collectors.toList());
    }

    private static List<DeletedMessage> getDeletedMessages() {
        return Stream.of(
                DeletedMessage.builder()
                        .title(MESSAGE_TITLE)
                        .details(MESSAGE_DETAILS)
                        .owner(getStudent())
                        .build(),
                DeletedMessage.builder()
                        .title(MESSAGE_TITLE)
                        .details(MESSAGE_DETAILS)
                        .owner(getStudent())
                        .build()
        ).collect(Collectors.toList());
    }

    private static List<DeletedMessageDTO> getDeletedMessageDTOs() {
        return getDeletedMessages()
                .stream()
                .map(deletedMessage -> messageMapper.deletedMessageToDeletedMessageDTO(deletedMessage))
                .collect(Collectors.toList());
    }

    public static ResponseMessage getRequestMessage() {
        return ResponseMessage.builder()
                .title(MESSAGE_TITLE)
                .details(MESSAGE_DETAILS)
                .receiverId(RECEIVER_ID)
                .senderId(SENDER_ID)
                .build();
    }

    public static UserDTO getStudentDTO() {
        return UserDTO.testBuilder()
                .id(STUDENT_ID)
                .firstName(STUDENT_NAME)
                .lastName(STUDENT_LAST_NAME)
                .build();
    }

    private static User getStudent() {
        return User.testBuilder()
                .id(STUDENT_ID)
                .firstName(STUDENT_NAME)
                .lastName(STUDENT_LAST_NAME)
                .build();
    }

    public static ResponseMessage getResponseMessage() {
        return ResponseMessage.builder()
                .title(MESSAGE_TITLE)
                .details(MESSAGE_DETAILS)
                .receiverId(STUDENT_ID)
                .senderId(STUDENT_ID)
                .build();
    }

    public static ReceivedMessage getFirstReceivedMessage() {
        return getReceivedMessages().get(0);
    }

    public static SentMessage getFirstSentMessage() {
        return getSentMessages().get(0);
    }

    public static DeletedMessage getDeletedMessage() {
        return getDeletedMessages().get(0);
    }

    public static ReceivedMessageDTO getFirstReceivedMessageDTO() {
        return getReceivedMessageDTOs().get(0);
    }

    public static SentMessageDTO getFirstSentMessageDTO() {
        return getSentMessageDTOs().get(0);
    }

    public static DeletedMessageDTO getFirstDeletedMessageDTO() {
        return getDeletedMessageDTOs().get(0);
    }




}
