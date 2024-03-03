package nz.ac.canterbury.seng302.gardenersgrove;

import nz.ac.canterbury.seng302.gardenersgrove.controller.RegisterFormController;
import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(RegisterFormController.class)
public class RegisterFormControllerTest {

    @MockBean
    private GardenerFormService gardenerFormService;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Gardener gardener;

    @Test
    void RegisterGardener_ValidInput_NewGardenerCreated() throws Exception {
//        Mockito.when(gardener.getId()).thenReturn(1L);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Kush")
                        .param("lastName", "Desai")
                        .param("email", "kush@gmail.com")
                        .param("DoB", String.valueOf(LocalDate.of(2004, 1, 15)))
                        .param("password", "Password1!")
                        .param("passwordConfirm", "Password1!"));
        assertEquals(1, gardenerFormService.getGardeners().size());
    }

}
