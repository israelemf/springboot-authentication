package com.auth.exceptions.handler;

import com.auth.exceptions.ErrorStructure;
import com.auth.exceptions.product.ProductNotFoundException;
import com.auth.exceptions.user.UserExistsException;
import com.auth.exceptions.user.UserNotFoundException;
import com.auth.exceptions.user.UserPasswordIncorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorStructure> globalExceptionHandler(RuntimeException exception) {
        ErrorStructure errorStructure = new ErrorStructure(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                List.of(exception.getMessage()));

        return new ResponseEntity<>(errorStructure, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorStructure> existsException(RuntimeException exception) {
        ErrorStructure errorStructure = new ErrorStructure(LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.name(),
                List.of(exception.getMessage()));

        return new ResponseEntity<>(errorStructure, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserPasswordIncorrectException.class)
    public ResponseEntity<ErrorStructure> passwordIncorrect(RuntimeException exception) {
        ErrorStructure errorStructure = new ErrorStructure(LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.name(),
                List.of(exception.getMessage()));

        return new ResponseEntity<>(errorStructure, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({UserNotFoundException.class,
            ProductNotFoundException.class})
    public ResponseEntity<ErrorStructure> notFoundException(RuntimeException exception) {
        ErrorStructure errorStructure = new ErrorStructure(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                List.of(exception.getMessage()));

        return new ResponseEntity<>(errorStructure, HttpStatus.NOT_FOUND);
    }

    /* Lançada quando a validação de um argumento de método falha(Por exemplo quando as anotações @NotNull e @NotEmpty)
    não são atendidas, e todas as mensagens referentes são coletadas e retornadas como uma lista. */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorStructure> argumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorList = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        ErrorStructure errorStructure = new ErrorStructure(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                List.of(exception.getMessage()));

        return new ResponseEntity<>(errorStructure, HttpStatus.BAD_REQUEST);
    }


}
