package nz.ac.canterbury.seng302.gardenersgrove.controller;

import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Controller for form example.
 * Note the @link{Autowired} annotation giving us access to the @link{GardenerFormService} class automatically
 */
@Controller
public class GardenerFormController {
    Logger logger = LoggerFactory.getLogger(GardenerFormController.class);

    private final GardenerFormService gardenerFormService;

    @Autowired
    public GardenerFormController(GardenerFormService gardenerFormService) {
        this.gardenerFormService = gardenerFormService;
    }
    /**
     * Gets form to be displayed, includes the ability to display results of previous form when linked to from POST form
     * @param displayFirstName previous first name entered into form to be displayed
     * @param displayLastName previous last name entered into form to be displayed
//     * @param displayDoB previous Date of Birth entered into form to be displayed
     * @param displayEmail previous Email entered into form to be displayed
     * @param displayPassword previous password entered into form to be displayed -- To be changed
     * @param model (map-like) representation of first name, last name, DoB, Email and password for use in thymeleaf
     * @return thymeleaf demoFormTemplate
     */
    @GetMapping("/form")
    public String form(@RequestParam(name="displayFirstName", required = false, defaultValue = "") String displayFirstName,
                       @RequestParam(name="displayLastName", required = false, defaultValue = "") String displayLastName,
//                       @RequestParam(name="displayDob", required = false, defaultValue = "") Date displayDoB,
                       @RequestParam(name="displayEmail", required = false, defaultValue = "") String displayEmail,
                       @RequestParam(name="displayPassword", required = false, defaultValue = "") String displayPassword,
                       Model model) {
        logger.info("GET /form");
        model.addAttribute("displayFirstName", displayFirstName);
        model.addAttribute("displayLastName", displayLastName);
//        model.addAttribute("displayDoB", displayDoB);
        model.addAttribute("displayEmail", displayEmail);
        model.addAttribute("displayPassword", displayPassword);
        return "demoFormTemplate";
    }

    /**
     * Posts a form response with name and favourite language
     * @param firstName first name of user
     * @param lastName last name of user
//     * @param DoB Date of Birth of user
     * @param email email of user
     * @param password password of user
     * @param model (map-like) representation of first name, last name, DoB, Email and password for use in thymeleaf,
     *              with values being set to relevant parameters provided
     * @return thymeleaf demoFormTemplate
     */
    @PostMapping("/form")
    public String submitForm( @RequestParam(name="first name") String firstName,
                              @RequestParam(name="last name") String lastName,
                              @RequestParam(name="2021-12-15") LocalDate DoB,
                              @RequestParam(name="name@gmail.com") String email,
                              @RequestParam(name="BadPassword") String password,
                              Model model) {
        logger.info("POST /form");
        gardenerFormService.addGardener(new Gardener(firstName, lastName, DoB , email, password));
        model.addAttribute("displayFirstName", firstName);
        model.addAttribute("displayLastName", lastName);
        model.addAttribute("displayDoB", DoB);
        model.addAttribute("displayEmail", email);
        model.addAttribute("displayPassword", password);
        return "demoFormTemplate";
    }

    /**
     * Gets all form responses
     * @param model (map-like) representation of results to be used by thymeleaf
     * @return thymeleaf demoResponseTemplate
     */
    @GetMapping("/form/responses")
    public String responses(Model model) {
        logger.info("GET /form/responses");
        model.addAttribute("responses", gardenerFormService.getGardeners());
        return "demoResponsesTemplate";
    }
}
