package br.com.profcon.infrastructure.controllers.professional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.profcon.application.dtos.professional.CreateProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalDTO;
import br.com.profcon.application.dtos.professional.ProfessionalFilterParamsDTO;
import br.com.profcon.application.dtos.professional.UpdateProfessionalDTO;
import br.com.profcon.application.http.HttpResponse;
import br.com.profcon.application.ports.controllers.professional.ProfessionalEndpointPort;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professionals")
@RestController
@RequestMapping("/v1/professionals")
public class ProfessionalController {

    private final ProfessionalEndpointPort professionalEndpoint;

    public ProfessionalController(ProfessionalEndpointPort professionalEndpoint) {
        this.professionalEndpoint = professionalEndpoint;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "It returns the created professional id ", content = {
                    @Content(mediaType = "application/json", examples = @ExampleObject(value = "1"))
            }),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to create the professional"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody final CreateProfessionalDTO inputDTO) {
        final HttpResponse response = this.professionalEndpoint.create(inputDTO);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It returns success when professional is updated"),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to update the professional"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") final Long id,
            @RequestBody final UpdateProfessionalDTO inputDTO) {
        final HttpResponse response = this.professionalEndpoint.update(id, inputDTO);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It returns success when professional is deleted"),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to delete the professional"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(
            @PathVariable("id") final Long id) {
        final HttpResponse response = this.professionalEndpoint.deleteById(id);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProfessionalDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to find the professional"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @PathVariable("id") final Long id) {
        final HttpResponse response = this.professionalEndpoint.findById(id);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProfessionalDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to list the professionals"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping
    public ResponseEntity<Object> listByParams(ProfessionalFilterParamsDTO paramsDTO) {
        final HttpResponse response = this.professionalEndpoint.listByParams(paramsDTO);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

}
