package be.marvel.code.coach.security;

import be.marvel.code.coach.infrastructure.exceptions.AuthenticationCredentialsNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final OnAuthenticationFailureHandler authenticationFailureHandler;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationFailureHandler = new OnAuthenticationFailureHandler();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var authentication = getAuthentication(request);

        if (authentication == null) {
            authenticationFailureHandler.onAuthenticationFailure(request, response, new BadCredentialsException("The provided credentials were invalid"));
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(SecurityConstants.TOKEN_HEADER);

        if (!isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {
                var signingkey = SecurityConstants.JWT_SECRET.getBytes();

                var parsedToken = Jwts.parser()
                        .setSigningKey(signingkey)
                        .parseClaimsJws(token.replace("Bearer ", ""));

                var email = parsedToken
                        .getBody()
                        .getSubject();

                ArrayList<LinkedHashMap<String, String>> authoritiesInToken
                        = parsedToken.getBody().get("rol", ArrayList.class);

                var authorities = authoritiesInToken.stream()
                        .map(linkedMap -> linkedMap.get("authority"))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                if (!isEmpty(email)) {
                    return new UsernamePasswordAuthenticationToken(email, null, authorities);
                }

            } catch (ExpiredJwtException exception) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
            } catch (SignatureException exception) {
                log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
            }
        }

        return null;
    }
}
