package br.com.profcon.application.services.professional;

import br.com.profcon.application.dtos.professional.UpdateProfessionalDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.professional.UpdateProfessionalUsecase;
import br.com.profcon.domain.Professional;

public class UpdateProfessionalService implements UpdateProfessionalUsecase {

    private final ProfessionalRepositoryPort professionalRepository;

    public UpdateProfessionalService(final ProfessionalRepositoryPort professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public void execute(Long id, UpdateProfessionalDTO inputDTO) {
        if (professionalRepository.findById(id).isEmpty()) {
            throw new ProfconRuleException(ProfconRuleException.PROFESSIONAL_NOT_FOUND);
        }
        professionalRepository.persist(Professional.update(id, inputDTO.name(), inputDTO.role(), inputDTO.birth()));
    }

}
