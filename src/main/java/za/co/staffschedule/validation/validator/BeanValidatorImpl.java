package za.co.staffschedule.validation.validator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BeanValidatorImpl implements BeanValidator {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    @Override
    public <T> List<String> validateErrorAsList(T bean, Class<?>... groups) {
        return getValidator().validate(bean, groups).stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

    @Override
    public <T> boolean isValid(T bean, Class<?>... groups) {
        return getValidator().validate(bean, groups).isEmpty();
    }

    @Override
    public <T> boolean isValidProperty(T propertyValue, String propertyName, Class<?>... groups) {
        return getValidator().validateProperty(propertyValue, propertyName, groups).isEmpty();
    }

    @Override
    public <T> void validate(T bean, Class<?>... groups) {
        List<String> errors = validateErrorAsList(bean, groups);
        if (CollectionUtils.isNotEmpty(errors)) throw new ValidationException(errors.toString());
    }

    @Override
    public <T> void validateLogErrors(T bean) {
        getValidator().validate(bean).forEach(violation -> log.error(violation.getMessage()));
    }

    private Validator getValidator() {
        return factory.getValidator();
    }
}
