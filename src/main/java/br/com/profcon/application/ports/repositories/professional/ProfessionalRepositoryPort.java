package br.com.profcon.application.ports.repositories.professional;

import java.util.List;
import java.util.Optional;

import br.com.profcon.domain.Professional;

public interface ProfessionalRepositoryPort {

    Professional persist(Professional professional);

    Long deleteById(Long id);

    Optional<Professional> findById(Long id);

    List<Professional> listByParams(String search);

}
