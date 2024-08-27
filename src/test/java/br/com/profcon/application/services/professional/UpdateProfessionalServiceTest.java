package br.com.profcon.application.services.professional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalDTO;
import br.com.profcon.application.dtos.professional.UpdateProfessionalDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.application.usecases.professional.FindProfessionalByIdUsecase;
import br.com.profcon.application.usecases.professional.UpdateProfessionalUsecase;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;
import br.com.profcon.infrastructure.database.inmemory.professional.ProfessionalRepositoryInMemory;

@DisplayName("Update professional tests")
@SpringBootTest
class UpdateProfessionalServiceTest {

    private CreateProfessionalUsecase createProfessional;
    private UpdateProfessionalUsecase updateProfessional;
    private FindProfessionalByIdUsecase findProfessionalById;
    private ProfessionalRepositoryPort professionalRepository;

    @BeforeEach
    public void before() {
        professionalRepository = new ProfessionalRepositoryInMemory();
        createProfessional = new CreateProfessionalService(professionalRepository);
        updateProfessional = new UpdateProfessionalService(professionalRepository);
        findProfessionalById = new FindProfessionalByIdService(professionalRepository);
    }

    @DisplayName("It should update the professional")
    @Test
    void itShouldUpdateTheProfessional() {
        final CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
        final Long id = createProfessional.execute(createProfessionalDTO);
        final UpdateProfessionalDTO updateProfessionalDTO = new UpdateProfessionalDTO("John Updated", ProfessionalRoleEnum.TESTER, LocalDate.of(1999, 2, 10));
        updateProfessional.execute(id, updateProfessionalDTO);
        final ProfessionalDTO updatedProfessionalDTO = findProfessionalById.execute(id);
        assertEquals(updateProfessionalDTO.name(), updatedProfessionalDTO.name());
        assertEquals(updateProfessionalDTO.role(), updatedProfessionalDTO.role());
        assertEquals(updateProfessionalDTO.birth(), updatedProfessionalDTO.birth());
    }

    @DisplayName("It should not update the professional when not found")
    @Test
    void itShouldNotUpdateTheProfessionalWhenNotFound() {
        String errorMessage = "";
        try {
            final UpdateProfessionalDTO updateProfessionalDTO = new UpdateProfessionalDTO("John Updated", ProfessionalRoleEnum.TESTER, LocalDate.of(1999, 2, 10));
            updateProfessional.execute(100l, updateProfessionalDTO);
        } catch (ProfconRuleException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(ProfconRuleException.PROFESSIONAL_NOT_FOUND, errorMessage);
    }

}
