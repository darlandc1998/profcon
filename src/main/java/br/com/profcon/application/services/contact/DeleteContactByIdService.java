package br.com.profcon.application.services.contact;

import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.application.usecases.contact.DeleteContactByIdUsecase;

public class DeleteContactByIdService implements DeleteContactByIdUsecase {

    private final ContactRepositoryPort contactRepository;

    public DeleteContactByIdService(final ContactRepositoryPort contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Long execute(Long id) {
        if (contactRepository.findById(id).isEmpty()) {
            throw new ProfconRuleException(ProfconRuleException.CONTACT_NOT_FOUND);
        }
        contactRepository.deleteById(id);
        return id;
    }

}
