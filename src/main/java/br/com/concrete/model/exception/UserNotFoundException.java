package br.com.concrete.model.exception;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
