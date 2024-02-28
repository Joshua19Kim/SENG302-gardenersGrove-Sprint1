package nz.ac.canterbury.seng302.gardenersgrove;

import nz.ac.canterbury.seng302.gardenersgrove.controller.LoginFormController;
import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginFormControllerTest {
    @Test
    void testStoringInfo() {
        String testPassword = "fakeMan1234";
        Gardener testGardener = new Gardener("fakeMan", "King", LocalDate.of(2000, 2,20), "fakeMan@gmail.com", testPassword);
        LoginFormController controller = new LoginFormController(null);
        String testResult = controller.storeUserInDataBase(testGardener);
        int hashedTestPassword = testPassword.hashCode();
        String result = "INSERT INTO gardener (fakeMan, King, 2000-02-20, fakeMan@gmail.com, "+ String.valueOf(hashedTestPassword)+ ")";
        assertEquals(testResult, result);

    }
}
