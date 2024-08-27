package br.com.profcon.application.http.models;

public class HttpResponseError {

    public final String messageError;

    public HttpResponseError(final String messageError) {
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }

}
