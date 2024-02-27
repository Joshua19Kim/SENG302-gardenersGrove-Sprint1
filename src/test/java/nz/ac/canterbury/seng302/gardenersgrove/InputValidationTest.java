package nz.ac.canterbury.seng302.gardenersgrove;

import nz.ac.canterbury.seng302.gardenersgrove.service.InputValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputValidationTest {

    @Test
    void testNormalName() {
        InputValidation validate = new InputValidation();
        String name = "Brad";
        boolean isValid = validate.checkValidName(name);
        assertTrue(isValid);
    }

    @Test
    void testLongName() {
        InputValidation validate = new InputValidation();
        String name = "Thisnameisdefinatelylongerthansixtyfourcharactersanditisgoingtofailthetest";
        boolean isValid = validate.checkValidName(name);
        assertFalse(isValid);
    }

    @Test
    void testNullName() {
        InputValidation validate = new InputValidation();
        String name = "";
        boolean isValid = validate.checkValidName(name);
        assertFalse(isValid);
    }

    @Test
    void testSingleCharacter() {
        InputValidation validate = new InputValidation();
        String name = "S";
        boolean isValid = validate.checkValidName(name);
        assertTrue(isValid);
    }

    @Test
    void testValidSpecialCharactersName() {
        InputValidation validate = new InputValidation();
        String name = "ÄÖÜäöüßĀĒĪŌŪāēīōū-'";
        boolean isValid = validate.checkValidName(name);
        assertTrue(isValid);
    }

    @Test
    void testNumbersInEmail() {
        InputValidation validate = new InputValidation();
        String email = "sky123@yahoo.com";
        boolean isValid = validate.checkValidEmail(email);
        assertTrue(isValid);
    }

    @Test
    void testInvalidSpecialCharacterName() {
        InputValidation validate = new InputValidation();
        String name = "%";
        boolean isValid = validate.checkValidName(name);
        assertFalse(isValid);
    }
    
    @Test
    void testNormalEmail() {
        InputValidation validate = new InputValidation();
        String email = "sda110@uclive.ac.nz";
        boolean isValid = validate.checkValidEmail(email);
        assertTrue(isValid);
    }

    @Test
    void testInvalidSpecialCharacterEmail() {
        InputValidation validate = new InputValidation();
        String email = "$@gmail.com";
        boolean isValid = validate.checkValidEmail(email);
        assertFalse(isValid);
    }


    @Test
    void testStartWithPeriod() {
        InputValidation validate = new InputValidation();
        String email = ".failure@gmail.com";
        boolean isValid = validate.checkValidEmail(email);
        assertFalse(isValid);
    }

    @Test
    void testEndWithPeriod() {
        InputValidation validate = new InputValidation();
        String email = "failure.@gmail.com";
        boolean isValid = validate.checkValidEmail(email);
        assertFalse(isValid);
    }

    @Test
    void ConsecutivePeriods() {
        InputValidation validate = new InputValidation();
        String email = "this...fails@gmail.com";
        boolean isValid = validate.checkValidEmail(email);
        assertFalse(isValid);
    }

    @Test
    void testValidHyphenInSuffix() {
        InputValidation validate = new InputValidation();
        String email = "username@hot-mail.co.nz";
        boolean isValid = validate.checkValidEmail(email);
        assertTrue(isValid);
    }

    @Test
    void testSuffixLongerThanSeven() {
        InputValidation validate = new InputValidation();
        String email = "longsuffix@uc.christchurch";
        boolean isValid = validate.checkValidEmail(email);
        assertFalse(isValid);
    }

}
