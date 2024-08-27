package br.com.profcon.infrastructure.database.postgresql.professional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepository extends JpaRepository<ProfessionalModel, Long> {

}
