package br.com.profcon.infrastructure.database.inmemory.professional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.profcon.application.ports.repositories.professional.ProfessionalRepositoryPort;
import br.com.profcon.domain.Professional;

public class ProfessionalRepositoryInMemory implements ProfessionalRepositoryPort {

    private Long fakeId = 1l;
    private List<Professional> professionals;

    public ProfessionalRepositoryInMemory() {
        this.professionals = new ArrayList<>();
    }

    @Override
    public Professional persist(Professional professional) {
        if (!this.professionals.contains(professional)) {
            professional.updateId(fakeId++);
            this.professionals.add(professional);
        } else {
            int index = this.professionals.indexOf(professional);
            this.professionals.set(index, professional);
        }
        return professional;
    }

    @Override
    public Long deleteById(Long id) {
        this.professionals.removeIf(professional -> professional.getId().equals(id));
        return id;
    }

    @Override
    public Optional<Professional> findById(Long id) {
        return this.professionals.stream().filter(professional -> professional.getId().equals(id)).findFirst();
    }

    @Override
    public List<Professional> listByParams(String search) {
        if (Objects.isNull(search)) {
            return this.professionals;
        }
        return this.professionals.stream().filter(professional -> professional.getName().contains(search) || professional.getRole().toString().contains(search)).toList();
    }

}
