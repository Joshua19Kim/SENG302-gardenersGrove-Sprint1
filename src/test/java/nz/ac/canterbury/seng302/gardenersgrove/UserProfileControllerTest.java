package nz.ac.canterbury.seng302.gardenersgrove;

import nz.ac.canterbury.seng302.gardenersgrove.controller.RegisterFormController;
import nz.ac.canterbury.seng302.gardenersgrove.controller.UserProfileController;
import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class UserProfileControllerTest {

    private UserProfileController userProfileController;
    private GardenerFormService gardenerFormService;
    private Model modelMock;

    @BeforeEach
    public void setUp() {
        gardenerFormService = Mockito.mock(GardenerFormService.class);
        userProfileController = new UserProfileController(gardenerFormService);
        modelMock = Mockito.mock(Model.class);
        assertTrue(true);
    }

    @Test
    void GivenValidGardenerEdit_WhenUserConfirms_GardenerEditUploaded() {

        userProfileController.submitForm("Ben", "Moore", LocalDate.of(2001, 11, 11),"new@new.new", false, modelMock);
        Mockito.verify(gardenerFormService, times(1)).addGardener(Mockito.any(Gardener.class));
    }

    @Test
    void GivenInvalidFirstNameEdit_WhenUserConfirms_GardenerEditNotUploaded() {
        userProfileController.submitForm("$#@", "Desai", LocalDate.of(2004, 1, 15),"test@gmail.com", false, modelMock);
        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
    }

    @Test
    void GivenInvalidLastNameEdit_WhenLastNameIsNotOptional_GardenerEditNotUploaded() {
        userProfileController.submitForm("Kush", "$#@", LocalDate.of(2004, 1, 15),"test@gmail.com", false, modelMock);
        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
    }

    @Test
    void GivenInvalidLastName_WhenLastNameIsOptional_NewGardenerCreated() {
        userProfileController.submitForm("Kush", "$#@", LocalDate.of(2004, 1, 15),"test@gmail.com", true, modelMock);
        Mockito.verify(gardenerFormService, times(1)).addGardener(Mockito.any(Gardener.class));
    }

    @Test
    void GivenAgeTooLow_WhenUserConfirms_GardenerEditNotUploaded() {
        userProfileController.submitForm("Kush", "Desai", LocalDate.of(2024, 1, 15),"test@gmail.com", true, modelMock);
        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
    }

    @Test
    void GivenAgeTooHigh_WhenUserConfirms_GardenerEditNotUploaded() {
        userProfileController.submitForm("Kush", "Desai", LocalDate.of(1024, 1, 15),"test@gmail.com", true, modelMock);
        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
    }
}
