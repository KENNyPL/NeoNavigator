package pl.cydo.neo.navigator.web.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();



        List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        grantedAuths.add(new SimpleGrantedAuthority(name));

        return new UsernamePasswordAuthenticationToken(name, password,grantedAuths);

        // use the credentials to try to authenticate against the third party system
//        if (authenticatedAgainstThirdPartySystem()) {
//            List<GrantedAuthority> grantedAuths = new ArrayList<>();
//            return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
//        } else {
//            throw new AuthenticationException("Unable to auth against third party systems");
//        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}