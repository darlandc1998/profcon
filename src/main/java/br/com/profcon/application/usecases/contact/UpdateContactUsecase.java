package br.com.profcon.application.usecases.contact;

import br.com.profcon.application.dtos.contact.UpdateContactDTO;

public interface UpdateContactUsecase {

    void execute(Long id, UpdateContactDTO inputDTO);

}
