package jbogo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = IpAddressValidator.class)
public @interface IpAddress {
    String message() default "The value '${validatedValue}' is not a valid IP address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}