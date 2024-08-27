package br.com.profcon.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.profcon.application.exceptions.ProfconRuleException;
import br.com.profcon.domain.enums.ProfessionalRoleEnum;

@DisplayName("Domain professionals tests")
@SpringBootTest
class ProfessionalTest {

    @DisplayName("It should create new professional")
    @Test
    void itShouldCreateNewProfessional() {
        final Professional professional = Professional.create("John", ProfessionalRoleEnum.DESENVOLVEDOR, LocalDate.of(1999, 10, 10));
        assertEquals("John", professional.getName());
        assertEquals(ProfessionalRoleEnum.DESENVOLVEDOR, professional.getRole());
        assertEquals(LocalDate.of(1999, 10, 10), professional.getBirth());
    }

    @DisplayName("It should not create new professional when name is null")
    @Test
    void itShouldNotCreateNewProfessionalWhenNameIsNull() {
        String errorMessage = "";
        try {
            Professional.create(null, ProfessionalRoleEnum.DESENVOLVEDOR, LocalDate.of(1999, 10, 10));
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("name_is_required", errorMessage);
    }

    @DisplayName("It should not create new professional when role is null")
    @Test
    void itShouldNotCreateNewProfessionalWhenRoleIsNull() {
        String errorMessage = "";
        try {
            Professional.create("John", null, LocalDate.of(1999, 10, 10));
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("role_is_required", errorMessage);
    }

    @DisplayName("It should not create new professional when birth is null")
    @Test
    void itShouldNotCreateNewProfessionalWhenBirthIsNull() {
        String errorMessage = "";
        try {
            Professional.create("John", ProfessionalRoleEnum.DESENVOLVEDOR, null);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("birth_is_required", errorMessage);
    }

    @DisplayName("It should not create new professional when birth is after than now")
    @Test
    void itShouldNotCreateNewProfessionalWhenBirthIsFuture() {
        String errorMessage = "";
        try {
            Professional.create("John", ProfessionalRoleEnum.DESENVOLVEDOR, LocalDate.now().plusYears(1));
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals(ProfconRuleException.BIRTH_MUST_BE_BEFORE_THAN_NOW, errorMessage);
    }

}
