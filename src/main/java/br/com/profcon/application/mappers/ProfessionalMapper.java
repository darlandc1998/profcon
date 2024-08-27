package br.com.profcon.application.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.application.dtos.professional.ProfessionalDTO;
import br.com.profcon.domain.Contact;
import br.com.profcon.domain.Professional;

public final class ProfessionalMapper {

    private ProfessionalMapper() {

    }

    public static ProfessionalDTO mapToDto(Professional professional) {
        return new ProfessionalDTO(
                professional.getId(),
                professional.getName(),
                professional.getRole(),
                professional.getBirth(),
                mapToContactDto(professional.getContacts()));
    }

    public static ProfessionalDTO toDto(Set<String> fields, Professional professional) {
        return new ProfessionalDTO(
                fields.contains("id") ? professional.getId() : null,
                fields.contains("name") ? professional.getName() : null,
                fields.contains("role") ? professional.getRole() : null,
                fields.contains("birth") ? professional.getBirth() : null,
                fields.contains("contacts") ? mapToContactDto(professional.getContacts()) : null);
    }

    private static Set<ContactDTO> mapToContactDto(Set<Contact> contacts) {
        return contacts
                .stream()
                .map(contact -> new ContactDTO(contact.getId(), contact.getName(), contact.getContact(), null))
                .collect(Collectors.toSet());
    }

}
