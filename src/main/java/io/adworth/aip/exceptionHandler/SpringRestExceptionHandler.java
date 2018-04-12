package io.adworth.aip.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.adworth.aip.helper.ResponseMessage;

@ControllerAdvice
public class SpringRestExceptionHandler {

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<?> handleBadRequestBody() {
        return ResponseMessage.response("Bad Request Body", HttpStatus.BAD_REQUEST);
    }
}
