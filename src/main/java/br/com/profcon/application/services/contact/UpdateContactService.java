package br.com.profcon.application.services.contact;

import br.com.profcon.application.dtos.contact.UpdateContactDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.contact.UpdateContactUsecase;
import br.com.profcon.domain.Contact;
import br.com.profcon.domain.Professional;

public class UpdateContactService implements UpdateContactUsecase {

    private final ContactRepositoryPort contactRepository;
    private final ProfessionalRepositoryPort professionalRepository;

    public UpdateContactService(
            ContactRepositoryPort contactRepository,
            ProfessionalRepositoryPort professionalRepository) {
        this.contactRepository = contactRepository;
        this.professionalRepository = professionalRepository;
    }

    @Override
    public void execute(Long id, UpdateContactDTO inputDTO) {
        final Professional professional = professionalRepository.findById(inputDTO.professionalId()).orElseThrow(() -> new ProfconRuleException(ProfconRuleException.PROFESSIONAL_NOT_FOUND));
        if (contactRepository.findById(id).isEmpty()) {
            throw new ProfconRuleException(ProfconRuleException.CONTACT_NOT_FOUND);
        }
        contactRepository.persist(Contact.update(id, inputDTO.name(), inputDTO.contact(), professional));
    }

}
