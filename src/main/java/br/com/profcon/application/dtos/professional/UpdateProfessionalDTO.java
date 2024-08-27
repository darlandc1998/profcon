package br.com.profcon.application.dtos.professional;

import java.time.LocalDate;

import br.com.profcon.domain.enums.ProfessionalRoleEnum;

public record UpdateProfessionalDTO(
        String name,
        ProfessionalRoleEnum role,
        LocalDate birth) {

}
