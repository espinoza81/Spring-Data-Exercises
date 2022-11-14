package gameshop.domain.user;

import gameshop.core.PasswordValidator;
import gameshop.exceptions.ValidationException;
import gameshop.messages.OutputMessages;

/**
 * Validate data for registering a user.
 * <p>
 * Email must contain @ sign and a period. It must be unique.
 * <p>
 * Password length must be at least 6 symbols
 * and must contain at least 1 uppercase, 1 lowercase letter and 1 digit.
 */
public class RegisterUserDto {

    private final String email;
    private final String password;
    private final String confirmPassword;
    private final String fullName;

    /**
     * input[0] skipped, because it is a command name
     *
     * @param input is the data taken from the console
     */
    public RegisterUserDto(String[] input) {
        this.email = input[1];
        this.password = input[2];
        this.confirmPassword = input[3];
        this.fullName = input[4];

        this.validate();
    }

    private void validate() {

        if (!password.equals(confirmPassword)) {
            throw new ValidationException(OutputMessages.PASSWORDS_DONT_MACH);
        }

        if (!OutputMessages.EMAIL_REGEX.matcher(email).find()) {
            throw new ValidationException(OutputMessages.INVALID_EMAIL_MESSAGE);
        }

        PasswordValidator passwordValidator = new PasswordValidator();
        if (!passwordValidator.isValid(password)) {

            String message = String.join(System.lineSeparator(), passwordValidator.getInvalidParameters());

            throw new ValidationException(message);
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }
}