package nz.ac.canterbury.seng302.gardenersgrove;

import nz.ac.canterbury.seng302.gardenersgrove.controller.UserProfileController;
import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import org.springframework.security.core.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.ui.Model;

import static org.mockito.Mockito.times;

public class UserProfileControllerTest {

    private UserProfileController userProfileController;
    private GardenerFormService gardenerFormService;
    private Model modelMock;


    @BeforeEach
    public void setUp() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        gardenerFormService = Mockito.mock(GardenerFormService.class);
        userProfileController = new UserProfileController(gardenerFormService);
        modelMock = Mockito.mock(Model.class);

    }

    @Test
    void GivenGardenerEmailExistingInServer_WhenToShowDetails_ControllerFindsDetailsWithEmail() {
        Gardener gardener = Mockito.mock(Gardener.class);
        userProfileController.getUserProfile(modelMock);
        Mockito.verify(gardenerFormService, times(1)).findByEmail(gardener.getEmail());
    }
    @Test
    void GivenGardenerEmailNotExistingInServer_WhenToShowDetails_ControllerCannotFindDetailsWithEmail() {
        Mockito.when(gardenerFormService.findByEmail("test@test.test")).thenThrow(NullPointerException.class);
        userProfileController.getUserProfile(modelMock);

    }



}
