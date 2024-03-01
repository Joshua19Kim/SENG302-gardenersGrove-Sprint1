package nz.ac.canterbury.seng302.gardenersgrove.controller;

import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;



@Controller
public class UserProfileController {
    Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    private GardenerFormService gardenerFormService;

    @Autowired
    public UserProfileController(GardenerFormService gardenerFormService) {
        this.gardenerFormService = gardenerFormService;
    }

    @GetMapping("/userProfile/{id}")
    public String getUserProfilePage(@PathVariable long id, Model model) {
        logger.info("GET /userProfile{id}");

        Optional<Gardener> gardenerOptional = this.gardenerFormService.findById(id);
        model.addAttribute("firstName", gardenerOptional.get().getFirstName());
        model.addAttribute("lastName", gardenerOptional.get().getLastName());
        model.addAttribute("DoB", gardenerOptional.get().getDoB());
        model.addAttribute("email", gardenerOptional.get().getEmail());

        return "userProfileTemplate";
    }





//<!--for editing part-->

//    @PostMapping("/userProfile")
//    public String submitForm( @RequestParam(name="firstName") String firstName,
//                              @RequestParam(name="lastName", required = false) String lastName,
//                              @RequestParam(name="DoB") LocalDate DoB,
//                              @RequestParam(name="email") String email,
//                              @RequestParam(name="password") String password,
//                              Model model) {
//        logger.info("POST /userProfile");
//        model.addAttribute("firstName", firstName);
//        model.addAttribute("lastName", lastName);
//        model.addAttribute("DoB", DoB);
//        model.addAttribute("email", email);
//        model.addAttribute("password", password);
//
//        // have to add "updating Gardener function"
//
//
//        return "userProfileTemplate";
//    }


}