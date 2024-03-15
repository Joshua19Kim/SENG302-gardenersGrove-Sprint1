package nz.ac.canterbury.seng302.gardenersgrove.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class DevClassController {

    private final Logger logger = LoggerFactory.getLogger(UserProfileController.class);


    @GetMapping("/redirectToDevPage")
    public RedirectView devButton() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authentication: " + authentication);
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return new RedirectView("/upload");
        }
        return new RedirectView("/login");
    }
}
