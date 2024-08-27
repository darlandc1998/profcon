package br.com.profcon.application.services.professional;

import br.com.profcon.application.dtos.professional.ProfessionalDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.mappers.ProfessionalMapper;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.professional.FindProfessionalByIdUsecase;
import br.com.profcon.domain.Professional;

public class FindProfessionalByIdService implements FindProfessionalByIdUsecase {

    private final ProfessionalRepositoryPort professionalRepository;

    public FindProfessionalByIdService(ProfessionalRepositoryPort professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public ProfessionalDTO execute(Long id) {
        final Professional professional = this.professionalRepository.findById(id).orElseThrow(() -> new ProfconRuleException(ProfconRuleException.PROFESSIONAL_NOT_FOUND));
        return ProfessionalMapper.mapToDto(professional);
    }

}
