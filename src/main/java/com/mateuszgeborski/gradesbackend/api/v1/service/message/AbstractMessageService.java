package com.mateuszgeborski.gradesbackend.api.v1.service.message;

import com.mateuszgeborski.gradesbackend.config.PaginationAndSortingConfig;
import com.mateuszgeborski.gradesbackend.api.v1.mapper.MessageMapper;
import com.mateuszgeborski.gradesbackend.api.v1.model.wrapper.MessageDTOs;
import com.mateuszgeborski.gradesbackend.domain.message.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMessageService {

    public final Integer pageLimit = PaginationAndSortingConfig.FETCH_LIMIT.get();

    private final MessageMapper messageMapper = MessageMapper.INSTANCE;

    public <T extends Message> MessageDTOs convertFetchedMessagesToMessageDTOs
            (Long messageId, List<T> messages) {

        if (messagesArePresent(messages)) {
            return MessageDTOs.builder()
                    .messageDTOs(
                            messages.stream()
                                    .map(messageMapper::messageToMessageDTO)
                                    .collect(Collectors.toList())
                    ).build();
        }
        throw new ResourceNotFoundException("No messages found for messageId " + messageId);
    }

    private <T extends Message> boolean messagesArePresent(List<T> optionalMessages) {
        return optionalMessages.size() != 0;
    }


    Pageable getPageableRequest (Integer page) {
        return PageRequest.of(page, pageLimit);

    }





}
