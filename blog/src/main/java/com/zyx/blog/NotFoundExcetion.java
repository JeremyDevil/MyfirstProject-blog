package com.zyx.blog;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExcetion extends RuntimeException{
    public NotFoundExcetion() {
        super();
    }

    public NotFoundExcetion(String message) {
        super(message);
    }

    public NotFoundExcetion(String message, Throwable cause) {
        super(message, cause);
    }
}
