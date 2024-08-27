package br.com.profcon.application.ports.repositories.contact;

import java.util.List;
import java.util.Optional;

import br.com.profcon.domain.Contact;

public interface ContactRepositoryPort {

    Contact persist(Contact contact);

    Long deleteById(Long id);

    Optional<Contact> findById(Long id);

    List<Contact> listByParams(String search);

}
