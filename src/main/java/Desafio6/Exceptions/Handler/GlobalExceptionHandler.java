package Desafio6.Exceptions.Handler;

import Desafio6.Exceptions.Dtos.ErrorMessageDTO;
import Desafio6.Exceptions.ExceptionsKind.UserBadRequestException;
import Desafio6.Exceptions.ExceptionsKind.UserCreateException;
import Desafio6.Exceptions.ExceptionsKind.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> defaultErrorHandler(HttpServletRequest req, Exception e) {
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> notFoundHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserBadRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> badRequestHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserCreateException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> createHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
