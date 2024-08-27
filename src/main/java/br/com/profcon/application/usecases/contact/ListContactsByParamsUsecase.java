package br.com.profcon.application.usecases.contact;

import java.util.List;

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.application.dtos.contact.ContactFilterParamsDTO;

public interface ListContactsByParamsUsecase {

    List<ContactDTO> execute(ContactFilterParamsDTO paramsDTO);

}
