package za.co.staffschedule.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;
import za.co.staffschedule.util.Constants;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    private static final String DATE_TIME_FORMAT_EXAMPLE = "17/05/2020 08:38:10";
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_FORMAT)
    @Schema(example = DATE_TIME_FORMAT_EXAMPLE)
    private LocalDateTime timestamp;
    private String message;
    private List<String> errors;

    public ApiErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    public ApiErrorResponse(HttpStatus status) {
        this();
        this.setStatus(status.value());
    }

    public ApiErrorResponse(HttpStatus status, String message) {
        this();
        this.setStatus(status.value());
        setMessage(message);
    }
}
