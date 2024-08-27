package br.com.profcon.application.dtos.professional;

import java.util.Set;

public record ProfessionalFilterParamsDTO(
        String q,
        Set<String> fields) {

}
