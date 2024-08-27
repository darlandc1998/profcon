package br.com.profcon.infrastructure.controllers.contact;

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

import br.com.profcon.application.dtos.contact.ContactDTO;
import br.com.profcon.application.dtos.contact.ContactFilterParamsDTO;
import br.com.profcon.application.dtos.contact.CreateContactDTO;
import br.com.profcon.application.dtos.contact.UpdateContactDTO;
import br.com.profcon.application.http.HttpResponse;
import br.com.profcon.application.ports.controllers.contact.ContactEndpointPort;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Contacts")
@RestController
@RequestMapping("/v1/contacts")
public class ContactController {

    private final ContactEndpointPort contactEndpoint;

    public ContactController(ContactEndpointPort contactEndpoint) {
        this.contactEndpoint = contactEndpoint;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "It returns the created contact id ", content = {
                    @Content(mediaType = "application/json", examples = @ExampleObject(value = "1"))
            }),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to create the contact"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody final CreateContactDTO inputDTO) {
        final HttpResponse response = this.contactEndpoint.create(inputDTO);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It returns success when contact is updated"),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to update the contact"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") final Long id,
            @RequestBody final UpdateContactDTO inputDTO) {
        final HttpResponse response = this.contactEndpoint.update(id, inputDTO);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It returns success when contact is deleted"),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to delete the contact"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(
            @PathVariable("id") final Long id) {
        final HttpResponse response = this.contactEndpoint.deleteById(id);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ContactDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to find the contact"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @PathVariable("id") final Long id) {
        final HttpResponse response = this.contactEndpoint.findById(id);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ContactDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Profcon rule error to list the contacts"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping
    public ResponseEntity<Object> listByParams(ContactFilterParamsDTO paramsDTO) {
        final HttpResponse response = this.contactEndpoint.listByParams(paramsDTO);
        return new ResponseEntity<>(response.getData(), HttpStatusCode.valueOf(response.getCode()));
    }

}
