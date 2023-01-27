package ru.sber.EducationManagementSystem.restController.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sber.EducationManagementSystem.exception.ExceptionMessageDto;
import ru.sber.EducationManagementSystem.exception.ItemNotFoundRestException;

@Slf4j
@RestControllerAdvice
public class ItemNotFoundRestHandler {

    @ExceptionHandler(ItemNotFoundRestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionMessageDto> handleItemNotFoundException(ItemNotFoundRestException exception) {

        log.error("ItemNotFoundRestException: {}", exception);

        ExceptionMessageDto exceptionMessageDto = new ExceptionMessageDto(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(exceptionMessageDto);
    }
}
