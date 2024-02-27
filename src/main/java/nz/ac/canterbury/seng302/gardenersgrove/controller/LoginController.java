package nz.ac.canterbury.seng302.gardenersgrove.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is a basic spring boot controller, note the @link{Controller} annotation which defines this.
 * This controller defines endpoints as functions with specific HTTP mappings
 */
@Controller
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * Redirects GET default url '/' to '/login'
     * @return redirect to /demo
     */
    @GetMapping("/")
    public String home() {
        logger.info("GET /");
        return "redirect:./login";
    }

    /**
     * Gets the thymeleaf page representing the /demo page (a basic welcome screen with some links)
     * @return thymeleaf demoTemplate
     */
    @GetMapping("/login")
    public String getLogin() {
        logger.info("GET /login");
        return "loginTemplate";
    }

}
