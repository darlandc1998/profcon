package br.com.profcon.application.services.contact;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.application.dtos.contact.ContactFilterParamsDTO;
import br.com.profcon.application.mappers.ContactMapper;
import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.application.usecases.contact.ListContactsByParamsUsecase;
import br.com.profcon.domain.Contact;

public class ListContactsByParamsService implements ListContactsByParamsUsecase {

    private final ContactRepositoryPort contactRepository;

    public ListContactsByParamsService(ContactRepositoryPort contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDTO> execute(ContactFilterParamsDTO paramsDTO) {
        final List<Contact> contacts = contactRepository.listByParams(Objects.nonNull(paramsDTO) ? paramsDTO.q() : null);
        return contacts
                .stream()
                .map(contact -> ContactMapper.toDto(Objects.nonNull(paramsDTO) && Objects.nonNull(paramsDTO.fields()) && !paramsDTO.fields().isEmpty() ? paramsDTO.fields() : defaultFieldsToReturn(),
                        contact))
                .toList();
    }

    private static Set<String> defaultFieldsToReturn() {
        return Set.of("id", "name", "contact", "professional");
    }

}
