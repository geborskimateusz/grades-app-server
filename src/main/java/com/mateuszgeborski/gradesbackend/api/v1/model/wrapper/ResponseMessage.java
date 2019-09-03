package com.mateuszgeborski.gradesbackend.api.v1.model.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessage {

    @JsonProperty("title")
    private String title;

    @JsonProperty("details")
    private String details;

    @JsonProperty("senderId")
    private Long senderId;

    @JsonProperty("receiverId")
    private Long receiverId;
}
