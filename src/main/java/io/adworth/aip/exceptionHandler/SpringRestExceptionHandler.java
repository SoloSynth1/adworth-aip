package io.adworth.aip.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import io.adworth.aip.helper.ResponseMessage;

@ControllerAdvice
public class SpringRestExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseEntity<?> handleNoMethodException(HttpServletRequest request,
            NoHandlerFoundException ex) {
        return ResponseMessage.response("Path Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public @ResponseBody ResponseEntity<?> handleDefaultException(Throwable ex, WebRequest req) {
    	String token = req.getParameter("token");
    	if (token != null && !token.isEmpty()) {
    		return ResponseMessage.response("Bad Request", HttpStatus.BAD_REQUEST, token);
    	}
        return ResponseMessage.response("Bad Request", HttpStatus.BAD_REQUEST);
    }
}