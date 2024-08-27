package br.com.profcon.application.dtos.contact;

public record CreateContactDTO(
        Long professionalId,
        String name,
        String contact) {

}
