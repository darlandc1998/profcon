package br.com.profcon.infrastructure.database.postgresql.contact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactModel, Long> {

}
