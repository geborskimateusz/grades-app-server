package com.mateuszgeborski.gradesbackend.api.v1.mapper;

import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.DeletedMessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.ReceivedMessageDTO;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.SentMessageDTO;
import com.mateuszgeborski.gradesbackend.domain.message.DeletedMessage;
import com.mateuszgeborski.gradesbackend.domain.message.Message;
import com.mateuszgeborski.gradesbackend.domain.message.ReceivedMessage;
import com.mateuszgeborski.gradesbackend.domain.message.SentMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "owner.address", ignore = true)
    @Mapping(target = "owner.contact", ignore = true)
    @Mapping(target = "owner.profileImage", ignore = true)
    MessageDTO messageToMessageDTO(Message message);

    @Mapping(target = "owner.address", ignore = true)
    @Mapping(target = "owner.contact", ignore = true)
    @Mapping(target = "owner.profileImage", ignore = true)
    MessageDTO receivedMessageToMessageDTO(ReceivedMessage receivedMessage);

    @Mapping(target = "owner.address", ignore = true)
    @Mapping(target = "owner.contact", ignore = true)
    @Mapping(target = "owner.profileImage", ignore = true)
    MessageDTO sentMessageToMessageDTO(SentMessage receivedMessage);

    @Mapping(target = "owner.address", ignore = true)
    @Mapping(target = "owner.contact", ignore = true)
    @Mapping(target = "owner.profileImage", ignore = true)
    MessageDTO deletedMessageToMessageDTO(DeletedMessage deletedMessage);

    @Mapping(target = "receiver.address", ignore = true)
    @Mapping(target = "receiver.contact", ignore = true)
    @Mapping(target = "receiver.profileImage", ignore = true)
    SentMessageDTO sentMessageToSentMessageDTO(SentMessage sentMessage);

    @Mapping(target = "sender.address", ignore = true)
    @Mapping(target = "sender.contact", ignore = true)
    @Mapping(target = "sender.profileImage", ignore = true)
    ReceivedMessageDTO receivedMessageToReceivedMessageDTO(ReceivedMessage receivedMessage);

    @Mapping(target = "senderOrReceiver.address", ignore = true)
    @Mapping(target = "senderOrReceiver.contact", ignore = true)
    @Mapping(target = "senderOrReceiver.profileImage", ignore = true)
    DeletedMessageDTO deletedMessageToDeletedMessageDTO(DeletedMessage deletedMessage);



    SentMessage messageToSentMessage(Message message);

    ReceivedMessage messageToReceivedMessage(Message message);

}
