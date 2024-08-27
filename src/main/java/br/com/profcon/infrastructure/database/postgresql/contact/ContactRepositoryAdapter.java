package br.com.profcon.infrastructure.database.postgresql.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.profcon.application.ports.repositories.contact.ContactRepositoryPort;
import br.com.profcon.domain.Contact;
import br.com.profcon.infrastructure.annotations.Adapter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Adapter
public class ContactRepositoryAdapter implements ContactRepositoryPort {

    private final ContactRepository repository;
    private final EntityManager entityManager;

    public ContactRepositoryAdapter(
            ContactRepository repository,
            EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public Contact persist(Contact contact) {
        final ContactModel model = repository.save(ContactModel.toModel(contact));
        contact.updateId(model.getId());
        return contact;
    }

    @Override
    public Long deleteById(Long id) {
        repository.deleteById(id);
        return id;
    }

    @Override
    public Optional<Contact> findById(Long id) {
        final Optional<ContactModel> model = repository.findById(id);
        if (model.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ContactModel.toDomain(model.get()));
    }

    @Override
    public List<Contact> listByParams(String search) {
        final CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ContactModel> query = criteria.createQuery(ContactModel.class);
        final Root<ContactModel> contactRoot = query.from(ContactModel.class);
        final List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(search) && search.length() > 0) {
            final Predicate nameLike = criteria.like(contactRoot.get("name"), "%" + search + "%");
            final Predicate contactLike = criteria.like(contactRoot.get("contact"), "%" + search + "%");
            predicates.add(criteria.or(nameLike, contactLike));
        }
        query.where(criteria.and(predicates.toArray(new Predicate[0])));
        final List<ContactModel> models = entityManager.createQuery(query).getResultList();
        return models.stream().map(ContactModel::toDomain).toList();
    }

}
