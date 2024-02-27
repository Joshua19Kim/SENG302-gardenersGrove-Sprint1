package nz.ac.canterbury.seng302.gardenersgrove.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is a basic spring boot controller, note the @link{Controller} annotation which defines this.
 * This controller defines endpoints as functions with specific HTTP mappings
 */
@Controller
public class RegisterController {
    Logger logger = LoggerFactory.getLogger(RegisterController.class);


    /**
     * Gets the thymeleaf page representing the /demo page (a basic welcome screen with some links)
     * @return thymeleaf demoTemplate
     */
    @GetMapping("/register")
    public String getRegister() {
        logger.info("GET /register");
        return "registerTemplate";
    }

}
