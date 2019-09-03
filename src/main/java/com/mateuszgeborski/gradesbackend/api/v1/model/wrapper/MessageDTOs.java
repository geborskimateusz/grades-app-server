package com.mateuszgeborski.gradesbackend.api.v1.model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mateuszgeborski.gradesbackend.api.v1.model.dto.message.MessageDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTOs {

    @JsonProperty("messages")
    private List<? extends MessageDTO> messageDTOs = new ArrayList<>();
}
