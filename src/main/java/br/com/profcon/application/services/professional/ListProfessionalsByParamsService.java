package br.com.profcon.application.services.professional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import br.com.profcon.application.dtos.professional.ProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalFilterParamsDTO;
import br.com.profcon.application.mappers.ProfessionalMapper;
import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.application.usecases.professional.ListProfessionalsByParamsUsecase;
import br.com.profcon.domain.Professional;

public class ListProfessionalsByParamsService implements ListProfessionalsByParamsUsecase {

    private final ProfessionalRepositoryPort professionalRepository;

    public ListProfessionalsByParamsService(final ProfessionalRepositoryPort professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public List<ProfessionalDTO> execute(ProfessionalFilterParamsDTO paramsDTO) {
        final List<Professional> professionals = professionalRepository.listByParams(Objects.nonNull(paramsDTO) ? paramsDTO.q() : null);
        return professionals
                .stream()
                .map(professional -> ProfessionalMapper
                        .toDto(Objects.nonNull(paramsDTO) && Objects.nonNull(paramsDTO.fields()) && !paramsDTO.fields().isEmpty() ? paramsDTO.fields() : defaultFieldsToReturn(), professional))
                .toList();
    }

    private static Set<String> defaultFieldsToReturn() {
        return Set.of("id", "name", "role", "birth", "contacts");
    }

}
