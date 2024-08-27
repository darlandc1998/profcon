package br.com.profcon.application.services.contact;

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.mappers.ContactMapper;
import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.application.usecases.contact.FindContactByIdUsecase;
import br.com.profcon.domain.Contact;

public class FindContactByIdService implements FindContactByIdUsecase {

    private final ContactRepositoryPort contactRepository;

    public FindContactByIdService(ContactRepositoryPort contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactDTO execute(Long id) {
        final Contact contact = contactRepository.findById(id).orElseThrow(() -> new ProfconRuleException(ProfconRuleException.CONTACT_NOT_FOUND));
        return ContactMapper.mapToDTO(contact);
    }

}
