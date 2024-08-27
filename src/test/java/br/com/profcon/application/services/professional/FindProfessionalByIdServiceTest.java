package br.com.profcon.application.services.professional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.application.usecases.professional.FindProfessionalByIdUsecase;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;
import br.com.profcon.infrastructure.database.inmemory.professional.ProfessionalRepositoryInMemory;

@DisplayName("Find professional tests")
@SpringBootTest
class FindProfessionalByIdServiceTest {

    private CreateProfessionalUsecase createProfessional;
    private FindProfessionalByIdUsecase findProfessionalById;
    private ProfessionalRepositoryPort professionalRepository;

    @BeforeEach
    public void before() {
        professionalRepository = new ProfessionalRepositoryInMemory();
        createProfessional = new CreateProfessionalService(professionalRepository);
        findProfessionalById = new FindProfessionalByIdService(professionalRepository);
    }

    @DisplayName("It should find the professional")
    @Test
    void itShouldFindTheProfessional() {
        final CreateProfessionalDTO createProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
        final Long id = createProfessional.execute(createProfessionalDTO);
        final ProfessionalDTO createdProfessionalDTO = findProfessionalById.execute(id);
        assertEquals(createProfessionalDTO.name(), createdProfessionalDTO.name());
        assertEquals(createProfessionalDTO.role(), createdProfessionalDTO.role());
        assertEquals(createProfessionalDTO.birth(), createdProfessionalDTO.birth());
    }

    @DisplayName("It should not find the professional")
    @Test
    void itShouldNotFindTheProfessional() {
        String errorMessage = "";
        try {
            findProfessionalById.execute(100l);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals(ProfconRuleException.PROFESSIONAL_NOT_FOUND, errorMessage);
    }
}
