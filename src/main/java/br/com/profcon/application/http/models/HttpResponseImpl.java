package br.com.profcon.application.http.models;

import br.com.profcon.application.http.HttpResponse;

public class HttpResponseImpl implements HttpResponse {

    private int code;
    private Object data;

    public HttpResponseImpl() {

    }

    public HttpResponseImpl(final int code, final Object data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(final Object data) {
        this.data = data;
    }

}
