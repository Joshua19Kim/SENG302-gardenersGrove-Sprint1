package nz.ac.canterbury.seng302.gardenersgrove.controller;

import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class UserProfileController {
    Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @GetMapping("/userProfile")
    public String getUserProfilePage(@RequestParam(name = "firstName", required = false, defaultValue = "") String firstName,
                                     @RequestParam(name = "lastName", required = false, defaultValue = "") String lastName,
                                     @RequestParam(name = "dob", required = false) LocalDate dob,
                                     @RequestParam(name = "email", required = false, defaultValue = "") String email,
                                     @RequestParam(name = "password", required = false, defaultValue = "") String password,
                                     Model model) {
        logger.info("GET /userProfile");
//        if (lastName.isEmpty()) {
//            lastName = "No last name";
//        }

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("dob", dob);
        model.addAttribute("email", email);
        model.addAttribute("password", password);

        return "userProfileTemplate";
    }
    @PostMapping("/userProfile")
    public String submitForm( @RequestParam(name="firstName") String firstName,
                              @RequestParam(name="lastName", required = false) String lastName,
                              @RequestParam(name="DoB") LocalDate DoB,
                              @RequestParam(name="email") String email,
                              @RequestParam(name="password") String password,
                              Model model) {
        logger.info("POST /userProfile");
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("DoB", DoB);
        model.addAttribute("email", email);
        model.addAttribute("password", password);

        // have to add "updating Gardener function"


        return "userProfileTemplate";
    }


}