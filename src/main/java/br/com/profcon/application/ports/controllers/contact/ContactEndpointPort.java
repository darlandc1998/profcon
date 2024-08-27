package br.com.profcon.application.ports.controllers.contact;

import br.com.profcon.application.dtos.contact.ContactFilterParamsDTO;
import br.com.profcon.application.dtos.contact.CreateContactDTO;
import br.com.profcon.application.dtos.contact.UpdateContactDTO;
import br.com.profcon.application.http.HttpResponse;

public interface ContactEndpointPort {

    HttpResponse create(CreateContactDTO inputDTO);

    HttpResponse update(Long id, UpdateContactDTO inputDTO);

    HttpResponse deleteById(Long id);

    HttpResponse findById(Long id);

    HttpResponse listByParams(ContactFilterParamsDTO paramsDTO);

}
