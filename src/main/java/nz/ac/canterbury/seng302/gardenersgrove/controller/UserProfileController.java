package nz.ac.canterbury.seng302.gardenersgrove.controller;

import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import nz.ac.canterbury.seng302.gardenersgrove.service.InputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Controller for User Profile.
 * Note the @link{Autowired} annotation giving us access to the @link{GardenerFormService} class automatically
 */

@Controller
public class UserProfileController {
    private final Logger logger = LoggerFactory.getLogger(UserProfileController.class);
    private final GardenerFormService gardenerFormService;

    private Gardener gardener;

    @Autowired
    public UserProfileController(GardenerFormService gardenerFormService) {
        this.gardenerFormService = gardenerFormService;
    }

    @GetMapping("/user")
    public String getUserProfile( Model model) {
        logger.info("GET /user");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        Optional<Gardener> gardenerOptional = gardenerFormService.findByEmail(currentUserEmail);
        gardener = gardenerOptional.get();
        model.addAttribute("firstName", gardener.getFirstName());
        model.addAttribute("lastName", gardener.getLastName());
        model.addAttribute("DoB", gardener.getDoB());
        model.addAttribute("email", gardener.getEmail());

        return "user";
    }
    @PostMapping("/user")
    public String submitForm(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "dob") LocalDate DoB,
            @RequestParam(name = "email") String email,
            Model model) {
        logger.info("POST /user");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        Optional<Gardener> gardenerOptional = gardenerFormService.findByEmail(currentUserEmail);
        Gardener gardener = gardenerOptional.get();
        model.addAttribute("firstName", gardener.getFirstName());
        model.addAttribute("lastName", gardener.getFirstName());
        model.addAttribute("DoB", gardener.getDoB());
        model.addAttribute("email", gardener.getEmail());



        return "redirect:editProfile";
    }


}