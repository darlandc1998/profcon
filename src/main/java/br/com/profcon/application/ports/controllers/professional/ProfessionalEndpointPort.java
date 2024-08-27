package br.com.profcon.application.ports.controllers.professional;

import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalFilterParamsDTO;
import br.com.profcon.application.dtos.professional.UpdateProfessionalDTO;
import br.com.profcon.application.http.HttpResponse;

public interface ProfessionalEndpointPort {

    HttpResponse create(CreateProfessionalDTO inputDTO);

    HttpResponse update(Long id, UpdateProfessionalDTO inputDTO);

    HttpResponse deleteById(Long id);

    HttpResponse findById(Long id);

    HttpResponse listByParams(ProfessionalFilterParamsDTO paramsDTO);

}
