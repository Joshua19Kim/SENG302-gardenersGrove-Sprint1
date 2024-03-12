package nz.ac.canterbury.seng302.gardenersgrove;

import nz.ac.canterbury.seng302.gardenersgrove.controller.EditProfileController;
import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import nz.ac.canterbury.seng302.gardenersgrove.service.InputValidationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

public class EditProfileControllerTest {

    private EditProfileController editProfileController;
    private GardenerFormService gardenerFormService;
    private Model modelMock;
    private Gardener gardener;
    private InputValidationService inputValidator;
    private Optional optional;


    @BeforeEach
    public void setUp() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        gardenerFormService = Mockito.mock(GardenerFormService.class);
        editProfileController = new EditProfileController(gardenerFormService);
        modelMock = Mockito.mock(Model.class);
        gardener = Mockito.mock(Gardener.class);
        inputValidator = Mockito.mock(InputValidationService.class);
        optional = Mockito.mock(Optional.class);
    }

    @Test
    void GivenValidGardenerEdit_WhenUserConfirms_GardenerEditUploaded() {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn(null);
        Mockito.when(gardenerFormService.findByEmail(Mockito.any())).thenReturn(Optional.ofNullable(gardener));
        Mockito.when(optional.get()).thenReturn(gardener);
        Mockito.when(inputValidator.checkValidEmail(Mockito.any())).thenReturn(Optional.empty());
        // ONLY works when the email is the same as the submitted one
        Mockito.when(gardener.getEmail()).thenReturn("new@new.new");
//        Mockito.when(SecurityContextHolder.getContext().getAuthentication()).thenReturn();
//        Gardener testGardener = new Gardener("Ben", "Moore", LocalDate.of(2001, 11, 11),"test@test.test", "password");
//        Authentication newAuth = new UsernamePasswordAuthenticationToken(testGardener.getEmail(), testGardener.getPassword(), testGardener.getAuthorities());
        editProfileController.submitForm("Ben", "Moore", LocalDate.of(2001, 11, 11),"new@new.new", false, modelMock);
        Mockito.verify(gardenerFormService, times(1)).addGardener(Mockito.any(Gardener.class));
    }

//    @Test
//    void GivenInvalidFirstNameEdit_WhenUserConfirms_GardenerEditNotUploaded() {
//        editProfileController.submitForm("$#@", "Desai", LocalDate.of(2004, 1, 15),"test@gmail.com", false, modelMock);
//        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
//    }
//
//    @Test
//    void GivenInvalidLastNameEdit_WhenLastNameIsNotOptional_GardenerEditNotUploaded() {
//        editProfileController.submitForm("Kush", "$#@", LocalDate.of(2004, 1, 15),"test@gmail.com", false, modelMock);
//        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
//    }
//
//    @Test
//    void GivenInvalidLastName_WhenLastNameIsOptional_NewGardenerCreated() {
//        editProfileController.submitForm("Kush", "$#@", LocalDate.of(2004, 1, 15),"test@gmail.com", false, modelMock);
//        Mockito.verify(gardenerFormService, times(1)).addGardener(Mockito.any(Gardener.class));
//    }
//
//    @Test
//    void GivenAgeTooLow_WhenUserConfirms_GardenerEditNotUploaded() {
//        editProfileController.submitForm("Kush", "Desai", LocalDate.of(2024, 1, 15),"test@gmail.com", false, modelMock);
//        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
//    }
//
//    @Test
//    void GivenAgeTooHigh_WhenUserConfirms_GardenerEditNotUploaded() {
//        editProfileController.submitForm("Kush", "Desai", LocalDate.of(1024, 1, 15),"test@gmail.com", false, modelMock);
//        Mockito.verify(gardenerFormService, Mockito.never()).addGardener(Mockito.any(Gardener.class));
//    }








}
