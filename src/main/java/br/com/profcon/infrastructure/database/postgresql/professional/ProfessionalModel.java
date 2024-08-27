package br.com.profcon.infrastructure.database.postgresql.professional;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import br.com.profcon.domain.Contact;
import br.com.profcon.domain.Professional;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;
import br.com.profcon.infrastructure.database.postgresql.contact.ContactModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "professionals")
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE professionals SET deleted = true WHERE id = ?")
public class ProfessionalModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ProfessionalRoleEnum role;
    @Column(name = "birth", nullable = false)
    private LocalDate birth;
    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<ContactModel> contacts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfessionalRoleEnum getRole() {
        return role;
    }

    public void setRole(ProfessionalRoleEnum role) {
        this.role = role;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public List<ContactModel> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactModel> contacts) {
        this.contacts = contacts;
    }

    public static ProfessionalModel toModel(final Professional professional) {
        final ProfessionalModel model = new ProfessionalModel();
        model.setId(professional.getId());
        model.setName(professional.getName());
        model.setRole(professional.getRole());
        model.setBirth(professional.getBirth());
        return model;
    }

    public static Professional toDomain(final ProfessionalModel model) {
        return Professional.restore(model.getId(), model.getName(), model.getRole(), model.getBirth(), toDomainContact(model.getContacts()));
    }

    private static Set<Contact> toDomainContact(List<ContactModel> contacts) {
        return contacts
                .stream()
                .map(contact -> Contact.restore(
                        contact.getId(),
                        contact.getName(),
                        contact.getContact(),
                        Professional.restore(
                                contact.getProfessional().getId(),
                                contact.getProfessional().getName(),
                                contact.getProfessional().getRole(),
                                contact.getProfessional().getBirth(),
                                null)))
                .collect(Collectors.toSet());
    }

}
