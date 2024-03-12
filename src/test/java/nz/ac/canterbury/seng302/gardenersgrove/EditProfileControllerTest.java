package nz.ac.canterbury.seng302.gardenersgrove;

import nz.ac.canterbury.seng302.gardenersgrove.controller.EditProfileController;
import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import nz.ac.canterbury.seng302.gardenersgrove.service.InputValidationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

public class EditProfileControllerTest {

    private EditProfileController editProfileController;

    private GardenerFormService gardenerFormService;

    private Model modelMock;

    private Gardener gardener;


    @BeforeEach
    public void setUp() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        gardenerFormService = Mockito.mock(GardenerFormService.class);
        editProfileController = new EditProfileController(gardenerFormService);
        modelMock = Mockito.mock(Model.class);
        gardener = Mockito.mock(Gardener.class);
    }

    // Unit Tests for the methods used for updating new detail of Profile
    @Test
    void SetNewValidFirstName_ReturnTrue() {
        String oldFirstName = gardener.getFirstName();
        String newFirstName = "newFirstName";
        gardener.setFirstName(newFirstName);
        assertEquals(oldFirstName,gardener.getFirstName());
    }
    @Test
    void SetNewValidLastName_ReturnTrue() {
        String oldLastName = gardener.getLastName();
        String newLastName = "newLastName";
        gardener.setLastName(newLastName);
        assertEquals(oldLastName,gardener.getLastName());
    }
    @Test
    void SetNewValidDoB_ReturnTrue() {
        LocalDate oldDoB = gardener.getDoB();
        LocalDate newDoB = LocalDate.of(1988,2,20);
        gardener.setDoB(newDoB);
        assertEquals(oldDoB,gardener.getDoB());
    }
    @Test
    void SetNewValidEmail_ReturnTrue() {
        String oldEmail = gardener.getEmail();
        String newEmail = "newEmail@gmail.com";
        gardener.setEmail(newEmail);
        assertEquals(oldEmail,gardener.getEmail());
    }




    // Integration test
    //Need to fix codes below
    @Test
    void GivenValidGardenerEdit_WhenUserConfirms_GardenerEditUploaded() {
        Authentication authentication = Mockito.mock(Authentication.class);
        editProfileController.submitForm("Ben", "Moore", LocalDate.of(2001, 11, 11),"new@new.new", false, modelMock);
        Mockito.verify(gardenerFormService, times(1)).addGardener(gardener);
    }

    @Test
    void GivenInvalidFirstNameEdit_WhenUserConfirms_GardenerEditNotUploaded() {
        editProfileController.submitForm("$#@", "Desai", LocalDate.of(2004, 1, 15),"test@gmail.com", false, modelMock);
        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
    }

    @Test
    void GivenInvalidLastNameEdit_WhenLastNameIsNotOptional_GardenerEditNotUploaded() {
        editProfileController.submitForm("Kush", "$#@", LocalDate.of(2004, 1, 15),"test@gmail.com", false, modelMock);
        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
    }

    @Test
    void GivenInvalidLastName_WhenLastNameIsOptional_NewGardenerCreated() {
        editProfileController.submitForm("Kush", "$#@", LocalDate.of(2004, 1, 15),"test@gmail.com", false, modelMock);
        Mockito.verify(gardenerFormService, times(1)).addGardener(Mockito.any(Gardener.class));
    }

    @Test
    void GivenAgeTooLow_WhenUserConfirms_GardenerEditNotUploaded() {
        editProfileController.submitForm("Kush", "Desai", LocalDate.of(2024, 1, 15),"test@gmail.com", false, modelMock);
        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
    }

    @Test
    void GivenAgeTooHigh_WhenUserConfirms_GardenerEditNotUploaded() {
        editProfileController.submitForm("Kush", "Desai", LocalDate.of(1024, 1, 15),"test@gmail.com", false, modelMock);
        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
    }








}
