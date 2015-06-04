package jbogo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Validate if a string is a valid ip-address.
 */
public class IpAddressValidator implements Validator, ConstraintValidator<IpAddress, String> {

    static private Pattern pattern = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.)"
        + "{3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");

    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!pattern.matcher(String.valueOf(target)).matches()) {
            errors.reject("503", "Invalid Ip address");
        }
    }

    @Override
    public void initialize(IpAddress ipAddress) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (s == null) || pattern.matcher(s).matches();
    }
}
