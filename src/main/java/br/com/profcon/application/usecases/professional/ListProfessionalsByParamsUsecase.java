package br.com.profcon.application.usecases.professional;

import java.util.List;

import br.com.profcon.application.dtos.professional.ProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalFilterParamsDTO;

public interface ListProfessionalsByParamsUsecase {

    List<ProfessionalDTO> execute(ProfessionalFilterParamsDTO paramsDTO);

}
