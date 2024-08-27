package br.com.profcon.application.services.professional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalFilterParamsDTO;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.application.usecases.professional.ListProfessionalsByParamsUsecase;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;
import br.com.profcon.infrastructure.database.inmemory.professional.ProfessionalRepositoryInMemory;

@DisplayName("List professional tests")
@SpringBootTest
class ListProfessionalsByParamsServiceTest {

    private CreateProfessionalUsecase createProfessional;
    private ListProfessionalsByParamsUsecase listProfessionalsByParams;
    private ProfessionalRepositoryPort professionalRepository;

    @BeforeEach
    public void before() {
        professionalRepository = new ProfessionalRepositoryInMemory();
        createProfessional = new CreateProfessionalService(professionalRepository);
        listProfessionalsByParams = new ListProfessionalsByParamsService(professionalRepository);
    }

    @DisplayName("It should list the contacts without params")
    @Test
    void itShouldListTheProfessionalsWithoutParams() {
        final CreateProfessionalDTO johnProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
        final CreateProfessionalDTO zohProfessionalDTO = new CreateProfessionalDTO("Zoh", ProfessionalRoleEnum.DESENVOLVEDOR, LocalDate.of(1999, 8, 22));
        final CreateProfessionalDTO lucProfessionalDTO = new CreateProfessionalDTO("Luc", ProfessionalRoleEnum.SUPORTE, LocalDate.of(2001, 1, 25));
        createProfessional.execute(johnProfessionalDTO);
        createProfessional.execute(zohProfessionalDTO);
        createProfessional.execute(lucProfessionalDTO);
        final List<ProfessionalDTO> professionals = listProfessionalsByParams.execute(null);
        assertEquals(johnProfessionalDTO.name(), professionals.get(0).name());
        assertEquals(zohProfessionalDTO.name(), professionals.get(1).name());
        assertEquals(lucProfessionalDTO.name(), professionals.get(2).name());
    }

    @DisplayName("It should list the contacts with params")
    @Test
    void itShouldListTheProfessionalsWithParams() {
        final CreateProfessionalDTO johnProfessionalDTO = new CreateProfessionalDTO("John", ProfessionalRoleEnum.DESIGNER, LocalDate.of(1999, 2, 10));
        final CreateProfessionalDTO zohProfessionalDTO = new CreateProfessionalDTO("Zoh", ProfessionalRoleEnum.DESENVOLVEDOR, LocalDate.of(1999, 8, 22));
        final CreateProfessionalDTO lucProfessionalDTO = new CreateProfessionalDTO("Luc", ProfessionalRoleEnum.SUPORTE, LocalDate.of(2001, 1, 25));
        createProfessional.execute(johnProfessionalDTO);
        createProfessional.execute(zohProfessionalDTO);
        createProfessional.execute(lucProfessionalDTO);
        final List<ProfessionalDTO> professionals = listProfessionalsByParams.execute(new ProfessionalFilterParamsDTO("Zoh", null));
        assertEquals(zohProfessionalDTO.name(), professionals.get(0).name());
    }

}
