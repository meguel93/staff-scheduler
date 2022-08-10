package za.co.staffschedule.validation.validator;

import java.util.List;

public interface BeanValidator {
    <T> void validateLogErrors(T bean);

    <T> List<String> validateErrorAsList(T bean, Class<?>... groups);

    <T> boolean isValid(T bean, Class<?>... groups);

    <T> boolean isValidProperty(T propertyValue, String propertyName, Class<?>... groups);

    <T> void validate(T bean, Class<?>... groups);

}
