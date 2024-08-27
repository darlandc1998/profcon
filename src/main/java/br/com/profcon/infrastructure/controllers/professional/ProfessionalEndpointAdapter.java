package br.com.profcon.infrastructure.controllers.professional;

import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalFilterParamsDTO;
import br.com.profcon.application.dtos.professional.UpdateProfessionalDTO;
import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.application.http.HttpResponse;
import br.com.profcon.application.http.handles.HttpHandle;
import br.com.profcon.application.ports.controllers.professional.ProfessionalEndpointPort;
import br.com.profcon.application.usecases.professional.CreateProfessionalUsecase;
import br.com.profcon.application.usecases.professional.DeleteProfessionalByIdUsecase;
import br.com.profcon.application.usecases.professional.FindProfessionalByIdUsecase;
import br.com.profcon.application.usecases.professional.ListProfessionalsByParamsUsecase;
import br.com.profcon.application.usecases.professional.UpdateProfessionalUsecase;
import br.com.profcon.infrastructure.annotations.Adapter;

@Adapter
public class ProfessionalEndpointAdapter implements ProfessionalEndpointPort {

    private final CreateProfessionalUsecase createProfessionalUsecase;
    private final UpdateProfessionalUsecase updateProfessionalUsecase;
    private final DeleteProfessionalByIdUsecase deleteProfessionalByIdUsecase;
    private final FindProfessionalByIdUsecase findProfessionalByIdUsecase;
    private final ListProfessionalsByParamsUsecase listProfessionalsByParamsUsecase;

    public ProfessionalEndpointAdapter(
            final CreateProfessionalUsecase createProfessionalUsecase,
            final UpdateProfessionalUsecase updateProfessionalUsecase,
            final DeleteProfessionalByIdUsecase deleteProfessionalByIdUsecase,
            final FindProfessionalByIdUsecase findProfessionalByIdUsecase,
            final ListProfessionalsByParamsUsecase listProfessionalsByParamsUsecase) {
        this.createProfessionalUsecase = createProfessionalUsecase;
        this.updateProfessionalUsecase = updateProfessionalUsecase;
        this.deleteProfessionalByIdUsecase = deleteProfessionalByIdUsecase;
        this.findProfessionalByIdUsecase = findProfessionalByIdUsecase;
        this.listProfessionalsByParamsUsecase = listProfessionalsByParamsUsecase;
    }

    @Override
    public HttpResponse create(CreateProfessionalDTO inputDTO) {
        try {
            return HttpHandle.created(this.createProfessionalUsecase.execute(inputDTO));
        } catch (ProfconRuleException e) {
            return HttpHandle.badRequest(e.getMessage());
        } catch (Exception e) {
            return HttpHandle.internalServerError(e.getMessage());
        }
    }

    @Override
    public HttpResponse update(Long id, UpdateProfessionalDTO inputDTO) {
        try {
            this.updateProfessionalUsecase.execute(id, inputDTO);
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
            this.deleteProfessionalByIdUsecase.execute(id);
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
            return HttpHandle.success(this.findProfessionalByIdUsecase.execute(id));
        } catch (ProfconRuleException e) {
            return HttpHandle.badRequest(e.getMessage());
        } catch (Exception e) {
            return HttpHandle.internalServerError(e.getMessage());
        }
    }

    @Override
    public HttpResponse listByParams(ProfessionalFilterParamsDTO paramsDTO) {
        try {
            return HttpHandle.success(this.listProfessionalsByParamsUsecase.execute(paramsDTO));
        } catch (ProfconRuleException e) {
            return HttpHandle.badRequest(e.getMessage());
        } catch (Exception e) {
            return HttpHandle.internalServerError(e.getMessage());
        }
    }

}
