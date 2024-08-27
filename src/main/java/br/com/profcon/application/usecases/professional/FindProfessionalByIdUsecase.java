package br.com.profcon.application.usecases.professional;

import br.com.profcon.application.dtos.professional.ProfessionalDTO;

public interface FindProfessionalByIdUsecase {

    ProfessionalDTO execute(Long id);

}
