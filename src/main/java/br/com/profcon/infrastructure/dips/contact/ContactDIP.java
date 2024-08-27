package br.com.profcon.infrastructure.dips.contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.services.contact.CreateContactService;
import br.com.profcon.application.services.contact.DeleteContactByIdService;
import br.com.profcon.application.services.contact.FindContactByIdService;
import br.com.profcon.application.services.contact.ListContactsByParamsService;
import br.com.profcon.application.services.contact.UpdateContactService;
import br.com.profcon.application.usecases.contact.CreateContactUsecase;
import br.com.profcon.application.usecases.contact.DeleteContactByIdUsecase;
import br.com.profcon.application.usecases.contact.FindContactByIdUsecase;
import br.com.profcon.application.usecases.contact.ListContactsByParamsUsecase;
import br.com.profcon.application.usecases.contact.UpdateContactUsecase;

@Configuration
public class ContactDIP {

    @Bean
    public CreateContactUsecase createContactUsecase(
            final ContactRepositoryPort contactRepository,
            final ProfessionalRepositoryPort professionalRepository) {
        return new CreateContactService(contactRepository, professionalRepository);
    }

    @Bean
    public UpdateContactUsecase updateContactUsecase(
            final ContactRepositoryPort contactRepository,
            final ProfessionalRepositoryPort professionalRepository) {
        return new UpdateContactService(contactRepository, professionalRepository);
    }

    @Bean
    public DeleteContactByIdUsecase deleteContactByIdUsecase(
            final ContactRepositoryPort contactRepository) {
        return new DeleteContactByIdService(contactRepository);
    }

    @Bean
    public FindContactByIdUsecase findContactByIdUsecase(
            final ContactRepositoryPort contactRepository) {
        return new FindContactByIdService(contactRepository);
    }

    @Bean
    public ListContactsByParamsUsecase listContactsByParamsUsecase(
            final ContactRepositoryPort contactRepository) {
        return new ListContactsByParamsService(contactRepository);
    }

}
