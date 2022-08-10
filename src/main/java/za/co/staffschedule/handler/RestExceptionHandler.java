package za.co.staffschedule.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import za.co.staffschedule.exception.ConflictException;
import za.co.staffschedule.exception.NotFoundException;
import za.co.staffschedule.response.ApiErrorResponse;
import za.co.staffschedule.service.ServiceException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ApiErrorResponse response = new ApiErrorResponse();
        response.setMessage(BAD_REQUEST.getReasonPhrase());
        response.setStatus(status.value());
        //Get all fields errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        response.setErrors(errors);
        return buildResponseEntity(response);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ApiErrorResponse response = new ApiErrorResponse(BAD_REQUEST, BAD_REQUEST.getReasonPhrase());
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException exception = (InvalidFormatException) ex.getCause();
            List<String> errors = new ArrayList<>();
            exception.getPath().forEach(field -> errors.add(String.format("A valid %s is required.", field.getFieldName())));
            response.setErrors(errors);
            return buildResponseEntity(response);
        }
        log.warn("Invalid data", ex.getCause());
        return buildResponseEntity(response);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(Exception ex) {
        return buildResponseEntity(getException(ex, NOT_FOUND));
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleUnauthorized(Exception exception) {
        return buildResponseEntity(getException(exception, FORBIDDEN));
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({ConflictException.class})
    protected ResponseEntity<Object> handleConflict(Exception exception) {
        return buildResponseEntity(getException(exception, CONFLICT));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ValidationException.class,
            IllegalArgumentException.class,
            ConstraintViolationException.class,
            HttpMessageConversionException.class})
    protected ResponseEntity<Object> handleBadRequestException(Exception exception) {
        return buildResponseEntity(getException(exception, BAD_REQUEST));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler({ServiceException.class, Exception.class})
    protected ResponseEntity<Object> handleInternalServer(Exception exception) {
        return buildResponseEntity(new ApiErrorResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }

    private ApiErrorResponse getException(Exception ex, HttpStatus status) {
        ApiErrorResponse restAPIException = new ApiErrorResponse(status);
        restAPIException.setMessage(ex.getMessage());
        restAPIException.setStatus(status.value());
        return restAPIException;
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
