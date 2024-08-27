package br.com.profcon.application.exceptions;

public class ProfconRuleException extends RuntimeException {

    public static final String CONTACT_NOT_FOUND = "contact_not_found";
    public static final String CONTACT_ALREADY_EXISTS = "contact_already_exists";
    public static final String PROFESSIONAL_NOT_FOUND = "professional_not_found";
    public static final String BIRTH_MUST_BE_BEFORE_THAN_NOW = "birth_must_be_before_than_now";

    private static final long serialVersionUID = 1L;

    public ProfconRuleException(String message) {
        super(message);
    }

}
