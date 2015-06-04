package jbogo.web.errorhandling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class NotUniqueException extends RuntimeException
{
    public NotUniqueException(String message)
    {
        super(message);
    }
}
