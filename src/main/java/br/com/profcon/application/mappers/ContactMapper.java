package br.com.profcon.application.mappers;

import java.util.Set;

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.application.dtos.contact.ContactProfessionalDTO;
import br.com.profcon.domain.Contact;

public final class ContactMapper {

    private ContactMapper() {

    }

    public static ContactDTO mapToDTO(Contact contact) {
        return new ContactDTO(contact.getId(),
                contact.getName(),
                contact.getContact(),
                new ContactProfessionalDTO(contact.getProfessional().getName(), contact.getProfessional().getRole()));
    }

    public static ContactDTO toDto(Set<String> fields, Contact contact) {
        return new ContactDTO(
                fields.contains("id") ? contact.getId() : null,
                fields.contains("name") ? contact.getName() : null,
                fields.contains("contact") ? contact.getContact() : null,
                fields.contains("professional")
                        ? new ContactProfessionalDTO(contact.getProfessional().getName(), contact.getProfessional().getRole())
                        : null);
    }

}
