package be.marvel.code.coach.security;

import be.marvel.code.coach.domain.entity.Role;
import be.marvel.code.coach.domain.entity.UserCredential;
import be.marvel.code.coach.infrastructure.exceptions.CorrectEmailButWrongPasswordException;
import be.marvel.code.coach.service.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CodeCoachAuthenticationProvider implements AuthenticationProvider {

    private final UserCredentialService userCredentialService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CodeCoachAuthenticationProvider(UserCredentialService userCredentialService, PasswordEncoder passwordEncoder) {
        this.userCredentialService = userCredentialService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserCredential user;

        try {
            user = userCredentialService.getUserByEmail(authentication.getPrincipal().toString());
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Email not found in system");
        }

        if (user != null) {
            String password = authentication.getCredentials().toString();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword(),
                        rolesToGrantedAuthorities(new ArrayList<>(user.getRoles())));
            } else {
                throw new CorrectEmailButWrongPasswordException("Incorrect password");
            }
        }

        throw new BadCredentialsException("The provided credentials were invalid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Collection<? extends GrantedAuthority> rolesToGrantedAuthorities(List<Role> roles) {
        List<Role> correctedRoles = new ArrayList<>(roles);

        if (roles.contains(Role.COACHEE) && roles.contains(Role.COACH)){ //TODO improve this
            correctedRoles.remove(Role.COACHEE);
        }

        return correctedRoles.stream()
                .flatMap(role -> role.getFeatureList().stream())
                .map(Enum::name).distinct()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
