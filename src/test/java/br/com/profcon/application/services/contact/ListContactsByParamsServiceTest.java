package br.com.profcon.application.services.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.application.dtos.contact.ContactFilterParamsDTO;
import br.com.profcon.application.dtos.contact.CreateContactDTO;
import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.services.professional.CreateProfessionalService;
import br.com.profcon.application.usecases.contact.CreateContactUsecase;
import br.com.profcon.application.usecases.contact.ListContactsByParamsUsecase;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;
import br.com.profcon.infrastructure.database.inmemory.contact.ContactRepositoryInMemory;
import br.com.profcon.infrastructure.database.inmemory.professional.ProfessionalRepositoryInMemory;

@DisplayName("List contact tests")
@SpringBootTest
class ListContactsByParamsServiceTest {

    private CreateContactUsecase createContact;
    private ListContactsByParamsUsecase listContactsByParams;
    private CreateProfessionalUsecase createProfessional;
    private ContactRepositoryPort contactRepository;
    private ProfessionalRepositoryPort professionalRepository;

    @BeforeEach
    public void before() {
        contactRepository = new ContactRepositoryInMemory();
        professionalRepository = new ProfessionalRepositoryInMemory();
        createProfessional = new CreateProfessionalService(professionalRepository);
        listContactsByParams = new ListContactsByParamsService(contactRepository);
        createContact = new CreateContactService(contactRepository, professionalRepository);
    }

    @DisplayName("It should list the contacts without params")
    @Test
    void itShouldListTheContactsWithoutParams() {
        final CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
        final Long professionalId = createProfessional.execute(createProfessionalDTO);
        final CreateContactDTO johnContactDTO = new CreateContactDTO(professionalId, "John", "Test");
        final CreateContactDTO zohContactDTO = new CreateContactDTO(professionalId, "Zoh", "Test");
        final CreateContactDTO lucContactDTO = new CreateContactDTO(professionalId, "Luc", "Test");
        createContact.execute(johnContactDTO);
        createContact.execute(zohContactDTO);
        createContact.execute(lucContactDTO);
        final List<ContactDTO> contacts = listContactsByParams.execute(null);
        assertEquals(johnContactDTO.name(), contacts.get(0).name());
        assertEquals(zohContactDTO.name(), contacts.get(1).name());
        assertEquals(lucContactDTO.name(), contacts.get(2).name());
    }

    @DisplayName("It should list the contacts with params")
    @Test
    void itShouldListTheContactsWithParams() {
        final CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
        final Long professionalId = createProfessional.execute(createProfessionalDTO);
        final CreateContactDTO johnContactDTO = new CreateContactDTO(professionalId, "John", "Test");
        final CreateContactDTO zohContactDTO = new CreateContactDTO(professionalId, "Zoh", "Test");
        final CreateContactDTO lucContactDTO = new CreateContactDTO(professionalId, "Luc", "Test");
        createContact.execute(johnContactDTO);
        createContact.execute(zohContactDTO);
        createContact.execute(lucContactDTO);
        final List<ContactDTO> contacts = listContactsByParams.execute(new ContactFilterParamsDTO("Luc", null));
        assertEquals(lucContactDTO.name(), contacts.get(0).name());
    }

}
