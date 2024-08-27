package br.com.profcon.application.services.professional;

import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.domain.Professional;

public class CreateProfessionalService implements CreateProfessionalUsecase {

    private final ProfessionalRepositoryPort professionalRepository;

    public CreateProfessionalService(final ProfessionalRepositoryPort professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Long execute(CreateProfessionalDTO inputDTO) {
        Professional professional = Professional.create(inputDTO.name(), inputDTO.role(), inputDTO.birth());
        professional = professionalRepository.persist(professional);
        return professional.getId();
    }

}
