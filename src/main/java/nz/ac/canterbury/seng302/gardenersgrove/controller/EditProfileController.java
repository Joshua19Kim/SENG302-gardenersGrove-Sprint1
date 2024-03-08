package nz.ac.canterbury.seng302.gardenersgrove.controller;

import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import nz.ac.canterbury.seng302.gardenersgrove.service.InputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;
@Controller
public class EditProfileController {

    Logger logger = LoggerFactory.getLogger(RegisterFormController.class);

    private final GardenerFormService gardenerFormService;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    @Autowired
    public EditProfileController(GardenerFormService gardenerFormService) {
        this.gardenerFormService = gardenerFormService;
    }

    private Gardener gardener;

    /**
     * Gets form to be displayed, includes the ability to display results of previous form when linked to from POST form
     *
     * @param firstName     first name of user to be entered in the form
     * @param lastName      last name of user
     * @param DoB           user's date of birth
     * @param email         user's email
     * @param lastNameCheck is the last name checkbox selected
     * @param model         (map-like) representation of name, language and isJava boolean for use in thymeleaf
     * @return thymeleaf demoFormTemplate
     */
    @GetMapping("/editProfile")
    public String form(@RequestParam(name = "firstName", required = false, defaultValue = "") String firstName,
                       @RequestParam(name = "lastName", required = false, defaultValue = "") String lastName,
                       @RequestParam(name = "DoB", required = false, defaultValue = "2024-01-01") LocalDate DoB,
                       @RequestParam(name = "email", required = false, defaultValue = "") String email,
                       @RequestParam(name = "lastNameCheck", required = false) boolean lastNameCheck,
                       Model model) {
        logger.info("GET /editProfile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        Optional<Gardener> gardenerOptional = gardenerFormService.findByEmail(currentUserEmail);
        gardener = gardenerOptional.get();
        model.addAttribute("firstName", gardener.getFirstName());
        model.addAttribute("lastName", gardener.getLastName());
        model.addAttribute("DoB", gardener.getDoB());
        model.addAttribute("email", gardener.getEmail());
        return "editProfile";
    }

    /**
     * Posts a form response with name and favourite language
     *
     * @param firstName first name of user
     * @param lastName  last name of user
     * @param DoB       user's date of birth
     * @param email     user's email
     * @return thymeleaf demoFormTemplate
     */
    @PostMapping("/editProfile")
    public String submitForm(@RequestParam(name = "firstName") String firstName,
                             @RequestParam(name = "lastName", required = false) String lastName,
                             @RequestParam(name = "DoB") LocalDate DoB,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "lastNameCheck", required = false) boolean lastNameCheck) {
        logger.info("POST /editProfile");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        Optional<Gardener> gardenerOptional = gardenerFormService.findByEmail(currentUserEmail);
        gardener = gardenerOptional.get();

        InputValidationService inputValidator = new InputValidationService(gardenerFormService);
        Optional<String> firstNameError = inputValidator.checkValidName(firstName, "First", false);
        Optional<String> lastNameError = inputValidator.checkValidName(lastName, "Last", lastNameCheck);

        if (!inputValidator.checkMinAge(DoB)) {
            return "editProfile";
        }
        if (!inputValidator.checkMaxAge(DoB)) {
            return "editProfile";
        }
        Optional<String> validEmailError = inputValidator.checkValidEmail(email);

        if (firstNameError.isEmpty() &&
                lastNameError.isEmpty() &&
                validEmailError.isEmpty()) {

            gardener.updateUserDetails(firstName, lastName, DoB, email);
            return "redirect:/user";
        }

        return "editProfile";

    }
}
//
//    /**
//     * Gets all form responses
//     * @param model (map-like) representation of results to be used by thymeleaf
//     * @return thymeleaf demoResponseTemplate
//     */
//    @GetMapping("/register/responses")
//    public String responses(Model model) {
//        logger.info("GET /form/responses");
//        model.addAttribute("responses", gardenerFormService.getGardeners());
//        return "registerResponsesTemplate";
//    }
//}