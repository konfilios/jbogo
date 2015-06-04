package jbogo.web.errorhandling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
public class NotFoundException extends RuntimeException
{
    public NotFoundException()
    {
    }

    public NotFoundException(String message)
    {
        super(message);
    }

    public NotFoundException(Object missingObject)
    {
        super(missingObject + " not found");
    }
}
