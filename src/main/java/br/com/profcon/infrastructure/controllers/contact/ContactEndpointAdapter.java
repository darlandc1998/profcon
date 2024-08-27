package br.com.profcon.infrastructure.controllers.contact;

import br.com.profcon.application.dtos.contact.ContactFilterParamsDTO;
import br.com.profcon.application.dtos.contact.CreateContactDTO;
import br.com.profcon.application.dtos.contact.UpdateContactDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.http.HttpResponse;
import br.com.profcon.application.http.handles.HttpHandle;
import br.com.profcon.application.ports.controllers.contact.ContactEndpointPort;
import br.com.profcon.application.usecases.contact.CreateContactUsecase;
import br.com.profcon.application.usecases.contact.DeleteContactByIdUsecase;
import br.com.profcon.application.usecases.contact.FindContactByIdUsecase;
import br.com.profcon.application.usecases.contact.ListContactsByParamsUsecase;
import br.com.profcon.application.usecases.contact.UpdateContactUsecase;
import br.com.profcon.infrastructure.annotations.Adapter;

@Adapter
public class ContactEndpointAdapter implements ContactEndpointPort {

    private final CreateContactUsecase createContactUsecase;
    private final UpdateContactUsecase updateContactUsecase;
    private final DeleteContactByIdUsecase deleteContactByIdUsecase;
    private final FindContactByIdUsecase findContactByIdUsecase;
    private final ListContactsByParamsUsecase listContactsByParamsUsecase;

    public ContactEndpointAdapter(
            CreateContactUsecase createContactUsecase,
            UpdateContactUsecase updateContactUsecase,
            DeleteContactByIdUsecase deleteContactByIdUsecase,
            FindContactByIdUsecase findContactByIdUsecase,
            final ListContactsByParamsUsecase listContactsByParamsUsecase) {
        this.createContactUsecase = createContactUsecase;
        this.updateContactUsecase = updateContactUsecase;
        this.deleteContactByIdUsecase = deleteContactByIdUsecase;
        this.findContactByIdUsecase = findContactByIdUsecase;
        this.listContactsByParamsUsecase = listContactsByParamsUsecase;
    }

    @Override
    public HttpResponse create(CreateContactDTO inputDTO) {
        try {
            return HttpHandle.created(this.createContactUsecase.execute(inputDTO));
        } catch (ProfconRuleException e) {
            return HttpHandle.badRequest(e.getMessage());
        } catch (Exception e) {
            return HttpHandle.internalServerError(e.getMessage());
        }
    }

    @Override
    public HttpResponse update(Long id, UpdateContactDTO inputDTO) {
        try {
            this.updateContactUsecase.execute(id, inputDTO);
            return HttpHandle.success();
        } catch (ProfconRuleException e) {
            return HttpHandle.badRequest(e.getMessage());
        } catch (Exception e) {
            return HttpHandle.internalServerError(e.getMessage());
        }
    }

    @Override
    public HttpResponse deleteById(Long id) {
        try {
            this.deleteContactByIdUsecase.execute(id);
            return HttpHandle.success();
        } catch (ProfconRuleException e) {
            return HttpHandle.badRequest(e.getMessage());
        } catch (Exception e) {
            return HttpHandle.internalServerError(e.getMessage());
        }
    }

    @Override
    public HttpResponse findById(Long id) {
        try {
            return HttpHandle.success(this.findContactByIdUsecase.execute(id));
        } catch (ProfconRuleException e) {
            return HttpHandle.badRequest(e.getMessage());
        } catch (Exception e) {
            return HttpHandle.internalServerError(e.getMessage());
        }
    }

    @Override
    public HttpResponse listByParams(ContactFilterParamsDTO paramsDTO) {
        try {
            return HttpHandle.success(this.listContactsByParamsUsecase.execute(paramsDTO));
        } catch (ProfconRuleException e) {
            return HttpHandle.badRequest(e.getMessage());
        } catch (Exception e) {
            return HttpHandle.internalServerError(e.getMessage());
        }
    }

}
