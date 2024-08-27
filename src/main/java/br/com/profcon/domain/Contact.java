package br.com.profcon.domain;

import java.util.Objects;

public class Contact {

    private Long id;
    private String name;
    private String contact;
    private Professional professional;

    public Contact(Long id, String name, String contact, Professional professional) {
        this.id = id;
        Objects.requireNonNull(name, "name_is_required");
        this.name = name;
        Objects.requireNonNull(contact, "contact_is_required");
        this.contact = contact;
        Objects.requireNonNull(professional, "professional_is_required");
        this.professional = professional;
    }

    public static Contact create(String name, String contact, Professional professional) {
        return new Contact(null, name, contact, professional);
    }

    public static Contact update(Long id, String name, String contact, Professional professional) {
        return new Contact(id, name, contact, professional);
    }

    public static Contact restore(Long id, String name, String contact, Professional professional) {
        return new Contact(id, name, contact, professional);
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

    public String getContact() {
        return contact;
    }

    public Professional getProfessional() {
        return professional;
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
        Contact other = (Contact) obj;
        return Objects.equals(id, other.id);
    }

}
