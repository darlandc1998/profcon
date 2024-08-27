package br.com.profcon.application.services.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.application.dtos.contact.CreateContactDTO;
import br.com.profcon.application.dtos.contact.UpdateContactDTO;
import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.services.professional.CreateProfessionalService;
import br.com.profcon.application.usecases.contact.CreateContactUsecase;
import br.com.profcon.application.usecases.contact.FindContactByIdUsecase;
import br.com.profcon.application.usecases.contact.UpdateContactUsecase;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;
import br.com.profcon.infrastructure.database.inmemory.contact.ContactRepositoryInMemory;
import br.com.profcon.infrastructure.database.inmemory.professional.ProfessionalRepositoryInMemory;

@DisplayName("Update contact tests")
@SpringBootTest
class UpdateContactServiceTest {

    private CreateContactUsecase createContact;
    private FindContactByIdUsecase findContactById;
    private UpdateContactUsecase updateContact;
    private CreateProfessionalUsecase createProfessional;
    private ContactRepositoryPort contactRepository;
    private ProfessionalRepositoryPort professionalRepository;

    @BeforeEach
    public void before() {
        contactRepository = new ContactRepositoryInMemory();
        professionalRepository = new ProfessionalRepositoryInMemory();
        createProfessional = new CreateProfessionalService(professionalRepository);
        findContactById = new FindContactByIdService(contactRepository);
        updateContact = new UpdateContactService(contactRepository, professionalRepository);
        createContact = new CreateContactService(contactRepository, professionalRepository);
    }

    @DisplayName("It should update the contact")
    @Test
    void itShouldUpdateTheContact() {
        final CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
        final Long professionalId = createProfessional.execute(createProfessionalDTO);
        final CreateContactDTO createContactDTO = new CreateContactDTO(professionalId, "John", "Test");
        final Long contactId = createContact.execute(createContactDTO);
        final UpdateContactDTO updateContactDTO = new UpdateContactDTO(professionalId, "John Updated", "Test Updated");
        updateContact.execute(contactId, updateContactDTO);
        final ContactDTO contactDTO = findContactById.execute(contactId);
        assertEquals(updateContactDTO.name(), contactDTO.name());
        assertEquals(updateContactDTO.contact(), contactDTO.contact());
    }

    @DisplayName("It should not update the contact when professional does not exist")
    @Test
    void itShouldNotUpdateTheContactWhenProfessionalDoesNotExist() {
        String errorMessage = "";
        try {
            final UpdateContactDTO updateContactDTO = new UpdateContactDTO(100l, "John Updated", "Test Updated");
            updateContact.execute(100l, updateContactDTO);
        } catch (ProfconRuleException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(ProfconRuleException.PROFESSIONAL_NOT_FOUND, errorMessage);
    }

    @DisplayName("It should not update the contact when it does not exist")
    @Test
    void itShouldNotUpdateTheContactWhenItDoesNotExist() {
        String errorMessage = "";
        try {
            final CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
            final Long professionalId = createProfessional.execute(createProfessionalDTO);
            final UpdateContactDTO updateContactDTO = new UpdateContactDTO(professionalId, "John Updated", "Test Updated");
            updateContact.execute(100l, updateContactDTO);
        } catch (ProfconRuleException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(ProfconRuleException.CONTACT_NOT_FOUND, errorMessage);
    }
}
