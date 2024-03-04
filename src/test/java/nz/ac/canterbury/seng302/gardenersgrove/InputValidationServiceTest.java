//package nz.ac.canterbury.seng302.gardenersgrove;
//
//import nz.ac.canterbury.seng302.gardenersgrove.service.InputValidationService;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class InputValidationServiceTest {
//
//    @Test
//    void testMatchingPassword() {
//        InputValidationService validate = new InputValidationService();
//        String password1 = "password";
//        String password2 = "password";
//        boolean isValid = validate.checkPasswordsMatch(password1, password2);
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testDifferentCapitalisation() {
//        InputValidationService validate = new InputValidationService();
//        String password1 = "password";
//        String password2 = "PASSWORD";
//        boolean isValid = validate.checkPasswordsMatch(password1, password2);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testDifferentPassword() {
//        InputValidationService validate = new InputValidationService();
//        String password1 = "HelloWorld";
//        String password2 = "ByeMoon";
//        boolean isValid = validate.checkPasswordsMatch(password1, password2);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testNullPassword() {
//        InputValidationService validate = new InputValidationService();
//        String password = "";
//        boolean isValid = validate.checkStrongPassword(password);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testLongPasswordNoSpecial() {
//        InputValidationService validate = new InputValidationService();
//        String password = "morethaneight";
//        boolean isValid = validate.checkStrongPassword(password);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testLongSpecialNoLetter() {
//        InputValidationService validate = new InputValidationService();
//        String password = "!@#$%^&**()";
//        boolean isValid = validate.checkStrongPassword(password);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testLongSpecialNoLowercase() {
//        InputValidationService validate = new InputValidationService();
//        String password = "ABCDEG!@#$";
//        boolean isValid = validate.checkStrongPassword(password);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testLongSpecialAlphaNoNumber() {
//        InputValidationService validate = new InputValidationService();
//        String password = "ABCDEG!@#$aa";
//        boolean isValid = validate.checkStrongPassword(password);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testValidPassword() {
//        InputValidationService validate = new InputValidationService();
//        String password = "ABC123sd!";
//        boolean isValid = validate.checkStrongPassword(password);
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testNormalName() {
//        InputValidationService validate = new InputValidationService();
//        String name = "Brad";
//        boolean isValid = validate.checkValidName(name);
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testLongName() {
//        InputValidationService validate = new InputValidationService();
//        String name = "Thisnameisdefinatelylongerthansixtyfourcharactersanditisgoingtofailthetest";
//        boolean isValid = validate.checkValidName(name);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testNullName() {
//        InputValidationService validate = new InputValidationService();
//        String name = "";
//        boolean isValid = validate.checkValidName(name);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testSingleCharacter() {
//        InputValidationService validate = new InputValidationService();
//        String name = "S";
//        boolean isValid = validate.checkValidName(name);
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testValidSpecialCharactersName() {
//        InputValidationService validate = new InputValidationService();
//        String name = "ÄÖÜäöüßĀĒĪŌŪāēīōū-'";
//        boolean isValid = validate.checkValidName(name);
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testNumbersInEmail() {
//        InputValidationService validate = new InputValidationService();
//        String email = "sky123@yahoo.com";
//        boolean isValid = validate.checkValidEmail(email);
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testInvalidSpecialCharacterName() {
//        InputValidationService validate = new InputValidationService();
//        String name = "%";
//        boolean isValid = validate.checkValidName(name);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testNormalEmail() {
//        InputValidationService validate = new InputValidationService();
//        String email = "sda110@uclive.ac.nz";
//        boolean isValid = validate.checkValidEmail(email);
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testInvalidSpecialCharacterEmail() {
//        InputValidationService validate = new InputValidationService();
//        String email = "$@gmail.com";
//        boolean isValid = validate.checkValidEmail(email);
//        assertFalse(isValid);
//    }
//
//
//    @Test
//    void testStartWithPeriod() {
//        InputValidationService validate = new InputValidationService();
//        String email = ".failure@gmail.com";
//        boolean isValid = validate.checkValidEmail(email);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testEndWithPeriod() {
//        InputValidationService validate = new InputValidationService();
//        String email = "failure.@gmail.com";
//        boolean isValid = validate.checkValidEmail(email);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void ConsecutivePeriods() {
//        InputValidationService validate = new InputValidationService();
//        String email = "this...fails@gmail.com";
//        boolean isValid = validate.checkValidEmail(email);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testValidHyphenInSuffix() {
//        InputValidationService validate = new InputValidationService();
//        String email = "username@hot-mail.co.nz";
//        boolean isValid = validate.checkValidEmail(email);
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testSuffixLongerThanSeven() {
//        InputValidationService validate = new InputValidationService();
//        String email = "longsuffix@uc.christchurch";
//        boolean isValid = validate.checkValidEmail(email);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testMatchingEmail() {
//        InputValidationService validate = new InputValidationService();
//        String email1 = "potato@gmail.com";
//        String email2 = "potato@gmail.com";
//        boolean isValid = validate.checkEmailsMatch(email1, email2);
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testDifferentCapitalisationEmail() {
//        InputValidationService validate = new InputValidationService();
//        String email1 = "potato@gmail.com";
//        String email2 = "POtato@gmail.COM";
//        boolean isValid = validate.checkEmailsMatch(email1, email2);
//        assertFalse(isValid);
//    }
//
//    @Test
//    void testDifferentEmail() {
//        InputValidationService validate = new InputValidationService();
//        String email1 = "HelloWorld@hotmail.com";
//        String email2 = "ByeWorld@canterbury.ac.nz";
//        boolean isValid = validate.checkEmailsMatch(email1, email2);
//        assertFalse(isValid);
//    }
//
//}
