package br.com.profcon.application.dtos.contact;

import java.util.Set;

public record ContactFilterParamsDTO(
        String q,
        Set<String> fields) {

}
