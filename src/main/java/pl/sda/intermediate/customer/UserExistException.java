package pl.sda.intermediate.customer;


public class UserExistException extends RuntimeException {

    public UserExistException(String message) {
        super(message);
    }
}
