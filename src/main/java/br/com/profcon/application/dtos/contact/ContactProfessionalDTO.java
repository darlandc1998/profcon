package br.com.profcon.application.dtos.contact;

import br.com.profcon.domain.enums.ProfessionalRoleEnum;

public record ContactProfessionalDTO(
        String name,
        ProfessionalRoleEnum role) {

}
