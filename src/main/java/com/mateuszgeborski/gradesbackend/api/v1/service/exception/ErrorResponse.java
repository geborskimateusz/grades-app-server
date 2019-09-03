package com.mateuszgeborski.gradesbackend.api.v1.service.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private int status;
    private String message;
    private long timeStamp;
}
