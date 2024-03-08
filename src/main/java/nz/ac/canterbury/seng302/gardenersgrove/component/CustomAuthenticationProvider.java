package nz.ac.canterbury.seng302.gardenersgrove.component;

import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.service.GardenerFormService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Custom Authentication Provider class, to allow for handling authentication in any way we see fit.
 * In this case using our existing {@link nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener}
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /**
     * User service for custom authentication using our own user objects
     */
    private final GardenerFormService gardenerFormService;

    /**
     * @param gardenerFormService Gardener service for custom authentication using our own user objects to be injected in
     */
    public CustomAuthenticationProvider(GardenerFormService gardenerFormService) {
        super();
        this.gardenerFormService = gardenerFormService;
    }

    /**
     * Custom authentication implementation
     *
     * @param authentication An implementation object that must have non-empty email (name) and password (credentials)
     * @return A new {@link UsernamePasswordAuthenticationToken} if email and password are valid with users authorities
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        String email = String.valueOf(authentication.getName());
        String password = String.valueOf(authentication.getCredentials());

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new BadCredentialsException("Bad Credentials");
        }

        Gardener u = gardenerFormService.getUserByEmailAndPassword(email, password).orElse(null);
        if (u == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(u.getEmail(), null, u.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

