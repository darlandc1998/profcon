package br.com.profcon.application.usecases.contact;

import br.com.profcon.application.dtos.contact.ContactDTO;

public interface FindContactByIdUsecase {

    ContactDTO execute(Long id);

}
