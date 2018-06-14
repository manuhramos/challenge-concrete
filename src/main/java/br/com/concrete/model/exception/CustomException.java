package br.com.concrete.model.exception;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
public class CustomException {
    
    private String messagem;

    public CustomException(String messagem){
        this.messagem = messagem;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
}
