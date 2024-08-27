package br.com.profcon.application.services.contact;

import br.com.profcon.application.dtos.contact.CreateContactDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.contact.CreateContactUsecase;
import br.com.profcon.domain.Contact;
import br.com.profcon.domain.Professional;

public class CreateContactService implements CreateContactUsecase {

    private final ContactRepositoryPort contactRepository;
    private final ProfessionalRepositoryPort professionalRepository;

    public CreateContactService(
            ContactRepositoryPort contactRepository,
            ProfessionalRepositoryPort professionalRepository) {
        this.contactRepository = contactRepository;
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Long execute(CreateContactDTO inputDTO) {
        final Professional professional = professionalRepository.findById(inputDTO.professionalId()).orElseThrow(() -> new ProfconRuleException(ProfconRuleException.PROFESSIONAL_NOT_FOUND));
        Contact contact = Contact.create(inputDTO.name(), inputDTO.contact(), professional);
        contact = contactRepository.persist(contact);
        return contact.getId();
    }

}
