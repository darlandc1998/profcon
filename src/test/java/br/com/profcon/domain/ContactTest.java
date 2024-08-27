package br.com.profcon.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.profcon.domain.enums.ProfessionalRoleEnum;

@DisplayName("Domain contacts tests")
@SpringBootTest
class ContactTest {

    @DisplayName("It should create new contact")
    @Test
    void itShouldCreateNewProfessional() {
        final Contact contact = Contact.create("John", "test", Professional.create("John", ProfessionalRoleEnum.DESENVOLVEDOR, LocalDate.of(1999, 10, 10)));
        assertEquals("John", contact.getName());
        assertEquals("test", contact.getContact());
        assertEquals(Professional.create("John", ProfessionalRoleEnum.DESENVOLVEDOR, LocalDate.of(1999, 10, 10)), contact.getProfessional());
    }

    @DisplayName("It should not create new contact when name is null")
    @Test
    void itShouldNotCreateNewContactWhenNameIsNull() {
        String errorMessage = "";
        try {
            Contact.create(null, "test", Professional.create("John", ProfessionalRoleEnum.DESENVOLVEDOR, LocalDate.of(1999, 10, 10)));
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("name_is_required", errorMessage);
    }

    @DisplayName("It should not create new contact when contact is null")
    @Test
    void itShouldNotCreateNewContactWhenContactIsNull() {
        String errorMessage = "";
        try {
            Contact.create("John", null, Professional.create("John", ProfessionalRoleEnum.DESENVOLVEDOR, LocalDate.of(1999, 10, 10)));
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("contact_is_required", errorMessage);
    }

    @DisplayName("It should not create new contact when professional is null")
    @Test
    void itShouldNotCreateNewContactWhenProfessionalIsNull() {
        String errorMessage = "";
        try {
            Contact.create("John", "test", null);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("professional_is_required", errorMessage);
    }

}
