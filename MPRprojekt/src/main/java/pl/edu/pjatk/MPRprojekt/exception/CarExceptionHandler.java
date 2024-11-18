package pl.edu.pjatk.MPRprojekt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CarExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( value = {CarNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {CarAlreadyExists.class})
    public ResponseEntity<Object> handleAlreadyExists(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {InvalidCarDataException.class})
    public ResponseEntity<Object> handleInvalidData(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
