package nz.ac.canterbury.seng302.gardenersgrove.service;

public class InputValidation {

    /**
     * Verify correctness of password entry by checking if match
     * @param passwordOne first password entered in form
     * @param passwordTwo second pass entered in form
     * @return boolean true for successful match or false if passwords do not match
     */
    public boolean checkPasswordsMatch (String passwordOne, String passwordTwo) {
        return passwordOne.equals(passwordTwo);
    }

    /**
     * Verify name user has input passes conditions:
     * - 0 < Name < 64
     * - Allows special characters umlauts, macrons, apostrophes, spaces
     * @param name provided by user input
     * @return true if passes verification
     */
    public boolean checkValidName (String name) {
        String nameRegex = "^[A-Za-zÄÖÜäöüßĀĒĪŌŪāēīōū]+[A-Za-zÄÖÜäöüßĀĒĪŌŪāēīōū' -]*$";
        return name.matches(nameRegex) && name.length() < 65;
    };

    /**
     * Verifies that email matches IETF guidelines on acceptable addresses
     * @param email provided by user input
     * @return true if passes verification
     */
    public boolean checkValidEmail (String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

}
