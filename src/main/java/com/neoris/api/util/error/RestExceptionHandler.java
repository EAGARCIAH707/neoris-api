package com.neoris.api.util.error;

import com.neoris.api.model.dto.ResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class RestExceptionHandler {

    private static final ConcurrentHashMap<String, HttpStatus> STATUS_CODES = new ConcurrentHashMap();

    public RestExceptionHandler() {
        STATUS_CODES.put(ConflictException.class.getSimpleName(), HttpStatus.CONFLICT);
        STATUS_CODES.put(
                NotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND);
        STATUS_CODES.put(HttpMessageNotReadableException.class.getSimpleName(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<Object> handleAllExceptions(
            Exception ex, WebRequest request, HttpServletRequest httpRequest) {
        return new ResponseEntity<>(
                ResponseErrorDto.builder()
                        .errorCode(0)
                        .timeResponse(new Date())
                        .message(ex.getMessage())
                        .path(httpRequest.getRequestURI())
                        .build(),
                !ObjectUtils.isEmpty(STATUS_CODES.get(ex.getClass().getSimpleName()))
                        ? STATUS_CODES.get(ex.getClass().getSimpleName())
                        : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<Object> handleValidationFieldsException(
            MethodArgumentNotValidException ex, WebRequest request, HttpServletRequest httpRequest) {
        return new ResponseEntity<>(
                ResponseErrorDto.builder()
                        .errorCode(0)
                        .timeResponse(new Date())
                        .message(ex.getBindingResult().getAllErrors().toString())
                        .path(httpRequest.getRequestURI())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}
