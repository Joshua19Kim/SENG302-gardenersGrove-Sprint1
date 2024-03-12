package nz.ac.canterbury.seng302.gardenersgrove;

import nz.ac.canterbury.seng302.gardenersgrove.controller.UserProfileController;
import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.ui.Model;

import static org.mockito.Mockito.times;
public class UserProfileControllerTest {

    private UserProfileController userProfileController;
    private GardenerFormService gardenerFormService;
    private Model modelMock;
    private Gardener gardener;
//    private StandaloneMockMvcBuilder mockMvc;


    @BeforeEach
    public void setUp() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        gardenerFormService = Mockito.mock(GardenerFormService.class);
        userProfileController = new UserProfileController(gardenerFormService);
        modelMock = Mockito.mock(Model.class);
        gardener = Mockito.mock(Gardener.class);
        gardener.setEmail("testSameEmail@test.test");
//        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserProfileController(gardenerFormService));



    }

    @Test
    void GivenGardenerEmailExistingInServer_WhenToShowDetails_ControllerFindsDetailsWithEmail() {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("testSameEmail@test.test");
        userProfileController.getUserProfile(modelMock);
        Mockito.verify(gardenerFormService, times(1)).findByEmail(gardener.getEmail());
    }
//    @Test
//    void GivenGardenerEmailNotExistingInServer_WhenToShowDetails_ControllerCannotFindDetailsWithEmail() throws Exception {
//
//        this.mockMvc.perform(get("/user").with(user("user"))).andExpect(model().attribute("firstName", Matchers.hasProperty("firstName", Matchers.equalTo("Not Registered"))));
//
//    }





}
