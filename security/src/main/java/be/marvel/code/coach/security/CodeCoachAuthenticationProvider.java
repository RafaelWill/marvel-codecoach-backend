package be.marvel.code.coach.security;

import be.marvel.code.coach.domain.entity.Role;
import be.marvel.code.coach.domain.entity.UserCredential;
import be.marvel.code.coach.service.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodeCoachAuthenticationProvider implements AuthenticationProvider {

    private final UserCredentialService userCredentialService;

    @Autowired
    public CodeCoachAuthenticationProvider(UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserCredential user;

        try {
            user = userCredentialService.getUserByEmail(authentication.getPrincipal().toString());
        } catch (Exception e) {
            throw new IllegalArgumentException("Email not found in system");
        }

        if (user != null) {
            String password = authentication.getCredentials().toString();
            if (password.equals(user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword(),
                        rolesToGrantedAuthorities(new ArrayList<>(user.getRoles())));
            } else {
                throw new IllegalArgumentException("Incorrect password");
            }
        }

        throw new BadCredentialsException("The provided credentials were invalid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Collection<? extends GrantedAuthority> rolesToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .flatMap(role -> role.getFeatureList().stream())
                .map(Enum::name).distinct()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}