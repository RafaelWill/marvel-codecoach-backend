package be.marvel.code.coach.security;

import be.marvel.code.coach.domain.entity.Person;
import be.marvel.code.coach.service.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final PersonService personService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationFailureHandler authenticationFailureHandler, PersonService personService) {
        this.authenticationManager = authenticationManager;
        this.personService = personService ;

        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var login = getLogin(request);
        var email = login.getEmail();
        var password = login.getPassword();

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        Person user;

        try {
            user = personService.getByEmail(authentication.getPrincipal().toString());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Email not found in system");
        }


        var token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(authentication.getName())
                .setExpiration(new Date(new Date().getTime() + 3600000)) // 1 hour
                .claim("rol", authentication.getAuthorities())
                .claim("userId", user.getId().toString())
                .compact();

        response.addHeader("Access-Control-Expose-Headers", "Authorization"); // exposes header
        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    }
    
    private LoginDto getLogin(HttpServletRequest request) {
        try {
            return new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
        } catch (IOException ex) {
            throw new RuntimeException("Could not read body from the incoming request", ex);
        }
    }
}
