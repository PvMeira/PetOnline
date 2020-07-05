package petonline.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {

    private  HttpStatus status;
    private  String message;
    private  Instant timestamp;

}