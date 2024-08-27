package br.com.profcon.application.services.professional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.application.usecases.professional.DeleteProfessionalByIdUsecase;
import br.com.profcon.application.usecases.professional.FindProfessionalByIdUsecase;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;
import br.com.profcon.infrastructure.database.inmemory.professional.ProfessionalRepositoryInMemory;

@DisplayName("Delete professional tests")
@SpringBootTest
class DeleteProfessionalByIdServiceTest {

    private CreateProfessionalUsecase createProfessional;
    private FindProfessionalByIdUsecase findProfessionalById;
    private DeleteProfessionalByIdUsecase deleteProfessionalById;
    private ProfessionalRepositoryPort professionalRepository;

    @BeforeEach
    public void before() {
        professionalRepository = new ProfessionalRepositoryInMemory();
        createProfessional = new CreateProfessionalService(professionalRepository);
        findProfessionalById = new FindProfessionalByIdService(professionalRepository);
        deleteProfessionalById = new DeleteProfessionalByIdService(professionalRepository);
    }

    @DisplayName("It should delete one professional")
    @Test
    void itShouldDeleteOneProfessional() {
        String messageError = "";
        try {
            final CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
            final Long id = createProfessional.execute(createProfessionalDTO);
            deleteProfessionalById.execute(id);
            findProfessionalById.execute(id);
        } catch (ProfconRuleException e) {
            messageError = e.getMessage();
        }
        assertEquals(ProfconRuleException.PROFESSIONAL_NOT_FOUND, messageError);
    }

    @DisplayName("It should not delete one professional when not found to delete")
    @Test
    void itShouldNotDeleteOneProfessionalWhenNotFoundToDelete() {
        String messageError = "";
        try {
            deleteProfessionalById.execute(100l);
        } catch (ProfconRuleException e) {
            messageError = e.getMessage();
        }
        assertEquals(ProfconRuleException.PROFESSIONAL_NOT_FOUND, messageError);
    }

}
