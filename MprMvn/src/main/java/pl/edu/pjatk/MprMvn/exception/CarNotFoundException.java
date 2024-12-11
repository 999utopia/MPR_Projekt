package pl.edu.pjatk.MprMvn.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException() {
        super("Car not found!");
    }
}
