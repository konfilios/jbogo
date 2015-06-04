package jbogo.web.errorhandling;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.Object;import java.lang.Override;import java.lang.String;import java.lang.Throwable;import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Enhances default error attributes for some special error types.
 */
public class EnhancedErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        // Initialize based on super class
        Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);

        // Fine-handling of special errors
        Throwable error = getError(requestAttributes);

        if (error instanceof ConstraintViolationException) {
            addErrorAttributes(errorAttributes, (ConstraintViolationException) error);
        }

        return errorAttributes;
    }

    /**
     * Add constraint violations as "errors" in the error attributes.
     *
     * @param errorAttributes
     * @param error
     */
    private void addErrorAttributes(Map<String, Object> errorAttributes, ConstraintViolationException error) {
        List<ObjectError> errors = new LinkedList<>();

        for (ConstraintViolation<?> violation : error.getConstraintViolations()) {
            errors.add(new ObjectError(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        errorAttributes.put("errors", errors);
    }
}
