package util;

import model.Author;

import java.time.LocalDate;
import java.util.List;

public class Validator {

    private void validateString(String fieldName, String value, List<String> errors) {
        if (value == null || value.trim().isEmpty()) {
            errors.add(fieldName + " should not be empty.");
        } else if (value.length() < 3) {
            errors.add(fieldName + " must contain at least 3 characters.");
        }
    }

    private void validateBirthDate(LocalDate birthDate, List<String> errors) {
        if (birthDate == null) {
            errors.add("Birth date is required.");
        } else if (birthDate.isAfter(LocalDate.now())) {
            errors.add("Birth date cannot be in the future.");
        }
    }

    public void validateAuthor(Author author, List<String> errors) {
        validateString("First Name", author.getFirstName(), errors);
        validateString("Last Name", author.getLastName(), errors);
        validateString("Email", author.getEmail(), errors);
        validateBirthDate(author.getBirthDay(), errors);

        if (author.getRole() == null) {
            errors.add("Role is required.");
        }
    }
}
