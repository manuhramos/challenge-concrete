package br.com.concrete.model.exception;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
public class InvalidPasswordException extends Exception {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
