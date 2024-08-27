package br.com.profcon.application.usecases.professional;

import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;

public interface CreateProfessionalUsecase {

    Long execute(CreateProfessionalDTO inputDTO);

}
