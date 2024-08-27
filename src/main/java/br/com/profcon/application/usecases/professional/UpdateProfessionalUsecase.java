package br.com.profcon.application.usecases.professional;

import br.com.profcon.application.dtos.professional.UpdateProfessionalDTO;

public interface UpdateProfessionalUsecase {

    void execute(Long id, UpdateProfessionalDTO inputDTO);

}
