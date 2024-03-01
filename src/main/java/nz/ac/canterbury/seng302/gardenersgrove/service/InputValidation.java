package nz.ac.canterbury.seng302.gardenersgrove.service;

import java.util.Optional;

import java.time.LocalDate;
import java.time.Period;

public class InputValidation {

    /**
     * Verify correctness of password entry by checking if match
     * @param passwordOne first password entered in form
     * @param passwordTwo second pass entered in form
     * @return boolean true for successful match or false if passwords do not match
     */
    public Optional<String> checkPasswordsMatch (String passwordOne, String passwordTwo) {
        return (passwordOne.equals(passwordTwo)) ? Optional.empty() : Optional.of("Passwords do not match.");
    }

    /**
     * Verify password entered is strong as given by U1-AC12
     * @param password attempted by user
     * @return true if strong, false if weak
     */
    public Optional<String> checkStrongPassword (String password) {
        String validRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[A-Za-z\\d\\W_]{9,}$";
        return (password.matches(validRegex) ? Optional.empty() : Optional.of("Your password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character."));
    }

    /**
     * Verify name user has input passes conditions:
     * - 0 < Name < 64
     * - Allows special characters umlauts, macrons, apostrophes, spaces
     * @param name provided by user input
     * @return true if passes verification
     */
    public Optional<String> checkValidName (String name, String firstOrLast, boolean lastNameCheck) {
        String nameRegex = "^[A-Za-zÄÖÜäöüßĀĒĪŌŪāēīōū]+[A-Za-zÄÖÜäöüßĀĒĪŌŪāēīōū' -]*$";
        if (lastNameCheck) {
            return Optional.empty();
        } else if (name.length() > 64) {
            return Optional.of(firstOrLast +" name must\n" +
                    "be 64 characters long or less");
        } else if (!name.matches(nameRegex)) {
            return Optional.of(firstOrLast + " name cannot be empty and must only include letters, spaces,\n" +
                    "hyphens or apostrophes");
        } else {
            return Optional.empty();
        }
    }

    /**
     * Verifies that email matches IETF guidelines on acceptable addresses
     * @param email provided by user input
     * @return true if passes verification
     */
    public Optional<String> checkValidEmail (String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return (email.matches(emailRegex) ? Optional.empty() : Optional.of("Email address must be in the form ‘jane@doe.nz"));

    }

    /**
     * Check emails match, done same as password check
     * @param emailOne
     * @param emailTwo
     * @return
     */
    public boolean checkEmailsMatch (String emailOne, String emailTwo) {
        return emailOne.equals(emailTwo);
    }

    /** Verifies that the user is old enough to register (13 years or more)
     * @param DoB
     * @return true if user is old enough
     */
    public boolean checkMinAge (LocalDate DoB) {
        return Period.between(DoB, LocalDate.now()).getYears() >= 13;
    }

    /** Verifies that the user is young enough to register (120 years or fewer)
     * @param DoB
     * @return true if user is young enough
     */
    public boolean checkMaxAge (LocalDate DoB) {
        return Period.between(DoB, LocalDate.now()).getYears() <= 120;
    }
}
