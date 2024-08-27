package br.com.profcon.application.services.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.application.dtos.contact.CreateContactDTO;
import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.services.professional.CreateProfessionalService;
import br.com.profcon.application.usecases.contact.CreateContactUsecase;
import br.com.profcon.application.usecases.contact.FindContactByIdUsecase;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;
import br.com.profcon.infrastructure.database.inmemory.contact.ContactRepositoryInMemory;
import br.com.profcon.infrastructure.database.inmemory.professional.ProfessionalRepositoryInMemory;

@DisplayName("Find contact tests")
@SpringBootTest
class FindContactByIdServiceTest {

    private CreateContactUsecase createContact;
    private FindContactByIdUsecase findContactById;
    private CreateProfessionalUsecase createProfessional;
    private ContactRepositoryPort contactRepository;
    private ProfessionalRepositoryPort professionalRepository;

    @BeforeEach
    public void before() {
        contactRepository = new ContactRepositoryInMemory();
        professionalRepository = new ProfessionalRepositoryInMemory();
        createProfessional = new CreateProfessionalService(professionalRepository);
        findContactById = new FindContactByIdService(contactRepository);
        createContact = new CreateContactService(contactRepository, professionalRepository);
    }

    @DisplayName("It should find the contact")
    @Test
    void itShouldFindTheContact() {
        final CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
        final Long professionalId = createProfessional.execute(createProfessionalDTO);
        final CreateContactDTO createContactDTO = new CreateContactDTO(professionalId, "John", "Test");
        final Long contactId = createContact.execute(createContactDTO);
        final ContactDTO contactDTO = findContactById.execute(contactId);
        assertEquals(createContactDTO.name(), contactDTO.name());
        assertEquals(createContactDTO.contact(), contactDTO.contact());
    }

    @DisplayName("It should not find the contact")
    @Test
    void itShouldNotFindTheContact() {
        String errorMessage = "";
        try {
            findContactById.execute(100l);
        } catch (ProfconRuleException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(ProfconRuleException.CONTACT_NOT_FOUND, errorMessage);
    }

}
