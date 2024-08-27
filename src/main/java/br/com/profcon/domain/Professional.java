package br.com.profcon.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;

public class Professional {

    private Long id;
    private String name;
    private ProfessionalRoleEnum role;
    private LocalDate birth;
    private Set<Contact> contacts;

    public Professional(Long id, String name, ProfessionalRoleEnum role, LocalDate birth, Set<Contact> contacts) {
        this.id = id;
        Objects.requireNonNull(name, "name_is_required");
        this.name = name;
        Objects.requireNonNull(role, "role_is_required");
        this.role = role;
        Objects.requireNonNull(birth, "birth_is_required");
        if (birth.isAfter(LocalDate.now())) {
            throw new ProfconRuleException(ProfconRuleException.BIRTH_MUST_BE_BEFORE_THAN_NOW);
        }
        this.birth = birth;
        this.contacts = contacts;
    }

    public static Professional create(String name, ProfessionalRoleEnum role, LocalDate birth) {
        return new Professional(null, name, role, birth, new HashSet<>());
    }

    public static Professional update(Long id, String name, ProfessionalRoleEnum role, LocalDate birth) {
        return new Professional(id, name, role, birth, new HashSet<>());
    }

    public static Professional restore(Long id, String name, ProfessionalRoleEnum role, LocalDate birth, Set<Contact> contacts) {
        return new Professional(id, name, role, birth, contacts);
    }

    public void addContact(Contact contact) {
        if (this.contacts.contains(contact)) {
            throw new ProfconRuleException(ProfconRuleException.CONTACT_ALREADY_EXISTS);
        }
        this.contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        if (!this.contacts.contains(contact)) {
            throw new ProfconRuleException(ProfconRuleException.CONTACT_NOT_FOUND);
        }
        this.contacts.remove(contact);
    }

    public void updateId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProfessionalRoleEnum getRole() {
        return role;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Professional other = (Professional) obj;
        return Objects.equals(id, other.id);
    }

}
