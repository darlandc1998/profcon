package br.com.profcon.application.dtos.professional;

import java.time.LocalDate;
import java.util.Set;

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;

public record ProfessionalDTO(
        Long id,
        String name,
        ProfessionalRoleEnum role,
        LocalDate birth,
        Set<ContactDTO> contacts) {
}
