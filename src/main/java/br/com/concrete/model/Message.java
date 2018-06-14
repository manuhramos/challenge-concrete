package br.com.concrete.model;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
public enum Message {
    EMAIL_INVALID("E-mail já existente"),
    SESSION_INVALID("Sessão inválida"),
    UNAUTHORIZED("Não autorizado"),
    USER_INVALID("Usuário e/ou senha inválidos");

    private String value;

    Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
