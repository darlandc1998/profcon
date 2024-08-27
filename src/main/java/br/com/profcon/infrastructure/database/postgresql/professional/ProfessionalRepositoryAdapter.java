package br.com.profcon.infrastructure.database.postgresql.professional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.domain.Professional;
import br.com.profcon.infrastructure.annotations.Adapter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Adapter
public class ProfessionalRepositoryAdapter implements ProfessionalRepositoryPort {

    private final ProfessionalRepository repository;
    private final EntityManager entityManager;

    public ProfessionalRepositoryAdapter(
            ProfessionalRepository repository,
            EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public Professional persist(Professional professional) {
        final ProfessionalModel model = repository.save(ProfessionalModel.toModel(professional));
        professional.updateId(model.getId());
        return professional;
    }

    @Override
    public Long deleteById(Long id) {
        repository.deleteById(id);
        return id;
    }

    @Override
    public Optional<Professional> findById(Long id) {
        final Optional<ProfessionalModel> model = repository.findById(id);
        if (model.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ProfessionalModel.toDomain(model.get()));
    }

    @Override
    public List<Professional> listByParams(String search) {
        final CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ProfessionalModel> query = criteria.createQuery(ProfessionalModel.class);
        final Root<ProfessionalModel> professionalRoot = query.from(ProfessionalModel.class);
        final List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(search) && search.length() > 0) {
            final Predicate nameLike = criteria.like(professionalRoot.get("name"), "%" + search + "%");
            final Predicate roleLike = criteria.like(professionalRoot.get("role"), "%" + search + "%");
            predicates.add(criteria.or(nameLike, roleLike));
        }
        query.where(criteria.and(predicates.toArray(new Predicate[0])));
        final List<ProfessionalModel> models = entityManager.createQuery(query).getResultList();
        return models
                .stream()
                .map(ProfessionalModel::toDomain).toList();
    }

}
