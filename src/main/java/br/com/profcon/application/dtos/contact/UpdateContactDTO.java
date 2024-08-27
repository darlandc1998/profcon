package br.com.profcon.application.dtos.contact;

public record UpdateContactDTO(
        Long professionalId,
        String name,
        String contact) {

}
