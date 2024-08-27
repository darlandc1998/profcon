package br.com.profcon.application.dtos.contact;

public record ContactDTO(
        Long id,
        String name,
        String contact,
        ContactProfessionalDTO professional) {

}
