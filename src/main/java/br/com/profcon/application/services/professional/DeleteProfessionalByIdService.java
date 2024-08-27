package br.com.profcon.application.services.professional;

import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.professional.DeleteProfessionalByIdUsecase;

public class DeleteProfessionalByIdService implements DeleteProfessionalByIdUsecase {

    private final ProfessionalRepositoryPort professionalRepository;

    public DeleteProfessionalByIdService(final ProfessionalRepositoryPort professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Long execute(Long id) {
        if (professionalRepository.findById(id).isEmpty()) {
            throw new ProfconRuleException(ProfconRuleException.PROFESSIONAL_NOT_FOUND);
        }
        professionalRepository.deleteById(id);
        return id;
    }

}
