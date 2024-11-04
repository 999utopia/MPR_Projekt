package pl.edu.pjatk.MPRprojekt.exception;

public class CarAlreadyExists extends RuntimeException{
    public CarAlreadyExists() {
        super("Car already exists!");
    }
}
