package br.com.profcon.infrastructure.database.postgresql.contact;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import br.com.profcon.domain.Contact;
import br.com.profcon.domain.Professional;
import br.com.profcon.infrastructure.database.postgresql.professional.ProfessionalModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "contacts")
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id = ?")
public class ContactModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_professional", referencedColumnName = "id")
    private ProfessionalModel professional;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "contact", nullable = false)
    private String contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfessionalModel getProfessional() {
        return professional;
    }

    public void setProfessional(ProfessionalModel professional) {
        this.professional = professional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public static ContactModel toModel(final Contact contact) {
        final ContactModel model = new ContactModel();
        model.setId(contact.getId());
        model.setName(contact.getName());
        model.setContact(contact.getContact());
        model.setProfessional(ContactModel.toProfessionalModel(contact.getProfessional()));
        return model;
    }

    private static ProfessionalModel toProfessionalModel(final Professional professional) {
        final ProfessionalModel model = new ProfessionalModel();
        model.setId(professional.getId());
        model.setName(professional.getName());
        model.setRole(professional.getRole());
        model.setBirth(professional.getBirth());
        return model;
    }

    public static Contact toDomain(final ContactModel model) {
        return Contact.restore(model.getId(), model.getName(), model.getContact(), ContactModel.toProfessionalDomain(model.getProfessional()));
    }

    private static Professional toProfessionalDomain(final ProfessionalModel model) {
        return Professional.restore(model.getId(), model.getName(), model.getRole(), model.getBirth(), null);
    }

}
