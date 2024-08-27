package br.com.profcon.infrastructure.database.inmemory.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.domain.Contact;

public class ContactRepositoryInMemory implements ContactRepositoryPort {

    private Long fakeId = 1l;
    private final List<Contact> contacts;

    public ContactRepositoryInMemory() {
        this.contacts = new ArrayList<>();
    }

    @Override
    public Contact persist(Contact contact) {
        if (!contacts.contains(contact)) {
            contact.updateId(fakeId++);
            this.contacts.add(contact);
        } else {
            int index = contacts.indexOf(contact);
            contacts.set(index, contact);
        }
        return contact;
    }

    @Override
    public Long deleteById(Long id) {
        this.contacts.removeIf(contact -> contact.getId().equals(id));
        return id;
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return this.contacts.stream().filter(contact -> contact.getId().equals(id)).findFirst();
    }

    @Override
    public List<Contact> listByParams(String search) {
        if (Objects.isNull(search)) {
            return this.contacts;
        }
        return this.contacts.stream().filter(contact -> contact.getName().contains(search) || contact.getContact().contains(search)).toList();
    }

}
