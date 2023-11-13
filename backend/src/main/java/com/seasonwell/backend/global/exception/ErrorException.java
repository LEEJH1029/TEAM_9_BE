package com.seasonwell.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorException {
    private ErrorCode errorCode;
}
