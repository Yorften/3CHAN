package util;

import model.Author;

import java.util.List;

public class Validator {


    private void validateString(String fieldName, String value, List<String> errors) {
        if (value == null || value.trim().isEmpty()) {
            errors.add("Filed should not be empty.");
        } else if (value.length() < 3) {
            errors.add("Please provide atleast 3 characters");
        }
    }

    private  void validationAothor(Author author){

    }


}
