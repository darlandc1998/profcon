package br.com.profcon.application.usecases.contact;

import br.com.profcon.application.dtos.contact.CreateContactDTO;

public interface CreateContactUsecase {

    Long execute(CreateContactDTO inputDTO);

}
