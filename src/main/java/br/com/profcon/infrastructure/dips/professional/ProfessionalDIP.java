package br.com.profcon.infrastructure.dips.professional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.services.professional.CreateProfessionalService;
import br.com.profcon.application.services.professional.DeleteProfessionalByIdService;
import br.com.profcon.application.services.professional.FindProfessionalByIdService;
import br.com.profcon.application.services.professional.ListProfessionalsByParamsService;
import br.com.profcon.application.services.professional.UpdateProfessionalService;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.application.usecases.professional.DeleteProfessionalByIdUsecase;
import br.com.profcon.application.usecases.professional.FindProfessionalByIdUsecase;
import br.com.profcon.application.usecases.professional.ListProfessionalsByParamsUsecase;
import br.com.profcon.application.usecases.professional.UpdateProfessionalUsecase;

@Configuration
public class ProfessionalDIP {

    @Bean
    public CreateProfessionalUsecase createProfessionalUsecase(
            final ProfessionalRepositoryPort professionalRepository) {
        return new CreateProfessionalService(professionalRepository);
    }

    @Bean
    public UpdateProfessionalUsecase updateProfessionalUsecase(
            final ProfessionalRepositoryPort professionalRepository) {
        return new UpdateProfessionalService(professionalRepository);
    }

    @Bean
    public DeleteProfessionalByIdUsecase deleteProfessionalByIdUsecase(
            final ProfessionalRepositoryPort professionalRepository) {
        return new DeleteProfessionalByIdService(professionalRepository);
    }

    @Bean
    public FindProfessionalByIdUsecase findProfessionalByIdUsecase(
            final ProfessionalRepositoryPort professionalRepository) {
        return new FindProfessionalByIdService(professionalRepository);
    }

    @Bean
    public ListProfessionalsByParamsUsecase listProfessionalsByParamsUsecase(
            final ProfessionalRepositoryPort professionalRepository) {
        return new ListProfessionalsByParamsService(professionalRepository);
    }

}
