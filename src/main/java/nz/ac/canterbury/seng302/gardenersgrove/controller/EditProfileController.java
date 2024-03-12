package nz.ac.canterbury.seng302.gardenersgrove.controller;

import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import nz.ac.canterbury.seng302.gardenersgrove.service.InputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private Gardener gardener;

    @Autowired
    public EditProfileController(GardenerFormService gardenerFormService) {
        this.gardenerFormService = gardenerFormService;

    }

    /**
     * Gets form to be displayed, includes the ability to display results of previous form when linked to from POST form
     *
     * @param model         (map-like) representation of name, language and isJava boolean for use in thymeleaf
     * @return thymeleaf demoFormTemplate
     */
    @GetMapping("/editProfile")
    public String form(Model model) {
        logger.info("GET /editProfile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        gardener = gardenerFormService.findByEmail(authentication.getName()).get();

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
     * @param model (map-like) representation of name, language and isJava boolean for use in thymeleaf,
     *              with values being set to relevant parameters provided
     * @return thymeleaf demoFormTemplate
     */
    @PostMapping("/editProfile")
    public String submitForm(@RequestParam(name = "firstName") String firstName,
                             @RequestParam(name = "lastName", required = false) String lastName,
                             @RequestParam(name = "DoB") LocalDate DoB,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "lastNameCheck", required = false) boolean lastNameCheck,
                             Model model) {
        logger.info("POST /editProfile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        gardener = gardenerFormService.findByEmail(authentication.getName()).get();

        InputValidationService inputValidator = new InputValidationService(gardenerFormService);

        Optional<String> firstNameError = inputValidator.checkValidName(firstName, "First", false);
        model.addAttribute("firstNameValid", firstNameError.orElse(""));
        Optional<String> lastNameError = inputValidator.checkValidName(lastName, "Last", lastNameCheck);
        model.addAttribute("lastNameValid", lastNameError.orElse(""));

        Optional<String> DoBError = inputValidator.checkDoB(DoB);
        model.addAttribute("DoBValid", DoBError.orElse(""));
        Optional<String> emailError = Optional.empty();
        if (!email.equals(gardener.getEmail())) {
            emailError = inputValidator.checkValidEmail(email);
        }
        model.addAttribute("emailValid", emailError.orElse(""));

        if (firstNameError.isEmpty() &&
                lastNameError.isEmpty() &&
                DoBError.isEmpty()&&
                emailError.isEmpty()) {

            gardener.setFirstName(firstName);
            gardener.setLastName(lastName);
            gardener.setEmail(email);
            gardener.setDoB(DoB);
            gardenerFormService.addGardener(gardener);
            // Re-authenticates user to catch case when they change their email
            Authentication newAuth = new UsernamePasswordAuthenticationToken(gardener.getEmail(), gardener.getPassword(), gardener.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication((newAuth));
            return "redirect:/user";
        }

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("DoB", DoB);
        model.addAttribute("email", email);
        return "editProfile";
    }
}
