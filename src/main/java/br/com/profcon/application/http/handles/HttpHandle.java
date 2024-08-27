package br.com.profcon.application.http.handles;

import org.springframework.http.HttpStatus;

import br.com.profcon.application.http.HttpResponse;
import br.com.profcon.application.http.models.HttpResponseBuilder;
import br.com.profcon.application.http.models.HttpResponseError;

public class HttpHandle {

    private HttpHandle() {

    }

    public static HttpResponse badRequest(final String error) {
        return HttpResponseBuilder
                .mount()
                .withCode(HttpStatus.BAD_REQUEST.value())
                .withData(new HttpResponseError(error))
                .build();
    }

    public static HttpResponse badRequest(final Object data) {
        return HttpResponseBuilder
                .mount()
                .withCode(HttpStatus.BAD_REQUEST.value())
                .withData(data)
                .build();
    }

    public static HttpResponse internalServerError(final String error) {
        return HttpResponseBuilder
                .mount()
                .withCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withData(new HttpResponseError(error))
                .build();
    }

    public static HttpResponse created(final Object data) {
        return HttpResponseBuilder
                .mount()
                .withCode(HttpStatus.CREATED.value())
                .withData(data)
                .build();
    }

    public static HttpResponse success() {
        return HttpResponseBuilder
                .mount()
                .withCode(HttpStatus.OK.value())
                .build();
    }

    public static HttpResponse success(final Object data) {
        return HttpResponseBuilder
                .mount()
                .withCode(HttpStatus.OK.value())
                .withData(data)
                .build();
    }

}
