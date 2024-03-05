//package nz.ac.canterbury.seng302.gardenersgrove.controller;
//
//import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
//import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.time.LocalDate;
//
///**
// * Controller for form example.
// * Note the @link{Autowired} annotation giving us access to the @link{FormService} class automatically
// */
//@Controller
//public class LoginFormController {
//    Logger logger = LoggerFactory.getLogger(LoginFormController.class);
//
//    private final GardenerFormService gardenerFormService;
//
//    @Autowired
//    public LoginFormController(GardenerFormService gardenerFormService) {
//        this.gardenerFormService = gardenerFormService;
//    }
//    /**
//     * Gets form to be displayed, includes the ability to display results of previous form when linked to from POST form
//     * @param firstName first name of user to be entered in the form
//     * @param lastName last name of user
//     * @param DoB user's date of birth
//     * @param email user's email
//     * @param password user's password
//     * @param model (map-like) representation of first name, last name, DoB, email and password for use in thymeleaf
//     * @return thymeleaf demoFormTemplate
//     */
//    @GetMapping("/login")
//    public String form(@RequestParam(name="firstName", required = false, defaultValue = "") String firstName,
//                       @RequestParam(name="lastName", required = false, defaultValue = "") String lastName,
//                       @RequestParam(name="DoB", required = false, defaultValue = "") LocalDate DoB,
//                       @RequestParam(name="email", required = false, defaultValue = "") String email,
//                       @RequestParam(name="password", required = false, defaultValue = "") String password,
//                       Model model) {
//        logger.info("GET /login");
//        logger.info(String.valueOf("1".hashCode()));
//        model.addAttribute("firstName", firstName);
//        model.addAttribute("lastName", lastName);
//        model.addAttribute("DoB", DoB);
//        model.addAttribute("email", email);
//        model.addAttribute("password", password);
//        return "loginTemplate";
//    }
//
////    /**
////     * Posts a form response with name and favourite language
////     * @param email user's email
////     * @param password user's password
////     * @param model (map-like) representation of first name, last name, DoB, email and password for use in thymeleaf
////     *              with values being set to relevant parameters provided
////     * @return thymeleaf demoFormTemplate
////     */
////    @PostMapping("/login")
////    public String submitForm( @RequestParam(name="email") String email,
////                              @RequestParam(name="password") String password,
////                              Model model) {
////        logger.info("POST /login");
////
////        model.addAttribute("email", email);
////        model.addAttribute("password", password);
////
////        return "loginTemplate";
////    }
//
//    /**
//     * Gets all form responses
//     * @param model (map-like) representation of results to be used by thymeleaf
//     * @return thymeleaf demoResponseTemplate
//     */
//    @GetMapping("/login/responses")
//    public String responses(Model model) {
//        logger.info("GET /form/responses");
//        model.addAttribute("responses", gardenerFormService.getGardeners());
//        return "registerResponsesTemplate";
//    }
//
//    /**
//     * Store user information into DataBase
//     * @param gardener getting user information
//     * @return String value including
//     */
//    public String storeUserInDataBase(Gardener gardener) {
//
//        //INSERT INTO gardener (first_name, last_name, DoB, email, password) VALUES ('Kush', 'Desai', DATE '2004-01-07', 'kush@gmail.com', 1);
//        return "INSERT INTO gardener (" + gardener.getFirstName() + ", "+ gardener.getLastName() + ", "
//                + gardener.getDoB().toString() + ", "+ gardener.getEmail() + ", "+ gardener.getPassword()+")";
//    }
//}
