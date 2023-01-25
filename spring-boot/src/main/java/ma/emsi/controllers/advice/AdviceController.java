package ma.emsi.controllers.advice;

import ma.emsi.exception.AlreadyExistsException;
import ma.emsi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //used to create a global exception handler for a Spring Boot RESTful application.
public class AdviceController {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, Object> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        return map;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsException.class)
    public Map<String, Object> handleAlreadyExistsException(AlreadyExistsException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        return map;
    }
}
