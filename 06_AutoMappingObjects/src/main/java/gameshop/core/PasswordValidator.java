package gameshop.core;

import gameshop.messages.OutputMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator {

    private static final Pattern PATTERN_LOWER = Pattern.compile("[a-z]");
    private static final Pattern PATTERN_UPPER = Pattern.compile("[A-Z]");
    private static final Pattern PATTERN_DIGIT = Pattern.compile("[0-9]");

    private static final Integer minLength = 6;
    private static final Integer maxLength = 30;

    private final List<String> invalidParameters;

    public PasswordValidator() {
        this.invalidParameters = new ArrayList<>();
    }

    public List<String> getInvalidParameters() {
        return Collections.unmodifiableList(invalidParameters);
    }

    public boolean isValid(final String password) {
        if (password == null) {
            invalidParameters.add(OutputMessages.PASSWORD_CANNOT_BE_NULL);
            return false;
        }

        if (password.length() < minLength) {
            invalidParameters.add(OutputMessages.PASSWORD_TOO_SHORT);
        }

        if (password.length() > maxLength) {
            invalidParameters.add(OutputMessages.PASSWORD_TOO_LONG);
        }

        if (!PATTERN_LOWER.matcher(password).find()) {
            invalidParameters.add(OutputMessages.PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER);
        }

        if (!PATTERN_UPPER.matcher(password).find()) {
            invalidParameters.add(OutputMessages.PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER);
        }

        if (!PATTERN_DIGIT.matcher(password).find()) {
            invalidParameters.add(OutputMessages.PASSWORD_SHOULD_CONTAIN_DIGIT);
        }

        return invalidParameters.isEmpty();
    }
}