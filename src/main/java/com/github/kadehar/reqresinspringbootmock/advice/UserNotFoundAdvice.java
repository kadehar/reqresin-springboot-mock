package com.github.kadehar.reqresinspringbootmock.advice;

import com.github.kadehar.reqresinspringbootmock.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Отвечает за возврат сообщения об ошибке и статуса 404,
 * если было поймано исключение UserNotFoundException.
 *
 * @author kadehar
 */
@ControllerAdvice
public class UserNotFoundAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(UserNotFoundException ex) {
        return ex.getMessage();
    }
}
