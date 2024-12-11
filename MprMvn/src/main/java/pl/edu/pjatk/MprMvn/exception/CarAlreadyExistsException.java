package pl.edu.pjatk.MprMvn.exception;

public class CarAlreadyExistsException extends RuntimeException{
    public CarAlreadyExistsException() {
        super("Car already exists!");
    }
}
