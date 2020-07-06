package petonline.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({ ApiException.class })
    protected ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.I_AM_A_TEAPOT, ex.getMessage(), Instant.now()), HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler({ SaleException.class })
    protected ResponseEntity<ApiErrorResponse> handleSaleException(SaleException ex) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), Instant.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

