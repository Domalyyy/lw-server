package ua.domaly.lwserver.filter;

import ch.qos.logback.core.util.OptionHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.domaly.lwserver.entity.MyUserDetails;
import ua.domaly.lwserver.service.UserService;
import ua.domaly.lwserver.utils.JwtUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;


/**
 * Class to filter JWT.
 */
@Log4j2
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain
    ) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (OptionHelper.isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwtUtils.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        final var user = userService
                .findByEmail(jwtUtils.getUsername(token))
                .orElseThrow(() -> new BadCredentialsException("Incorrect token"));

        final var userDetails = new MyUserDetails(user);

        final var authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                Optional.of(userDetails).map(UserDetails::getAuthorities).orElse(Collections.emptyList())
        );

        final var webAuthenticationDetails = new WebAuthenticationDetailsSource().buildDetails(request);
        authentication.setDetails(webAuthenticationDetails);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
