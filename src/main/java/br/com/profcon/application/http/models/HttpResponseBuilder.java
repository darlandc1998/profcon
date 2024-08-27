package br.com.profcon.application.http.models;

public class HttpResponseBuilder {

    private HttpResponseImpl httpResponse;

    public static HttpResponseBuilder mount() {
        HttpResponseBuilder builder = new HttpResponseBuilder();
        builder.httpResponse = new HttpResponseImpl();
        return builder;
    }

    public HttpResponseBuilder withCode(final int code) {
        httpResponse.setCode(code);
        return this;
    }

    public HttpResponseBuilder withData(final Object data) {
        httpResponse.setData(data);
        return this;
    }

    public HttpResponseImpl build() {
        return this.httpResponse;
    }

}
