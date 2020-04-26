package org.devs.heythere_backend.jwt;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devs.heythere_backend.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@NoArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired private JwtTokenProvider jwtTokenProvider;
    @Autowired private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            final String token = getJwtFromRequest(request);
            log.info("Token => {}", token);
            if (jwtTokenProvider.validateToken(token)) {
                final Long userId = jwtTokenProvider.getUserIdFromJwt(token);

                final UserDetails userDetails = customUserDetailsService.loadUserById(userId);

                final UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Could not set user authentication in security context", e);
        }

        filterChain.doFilter(request,response);
    }

    private String getJwtFromRequest(final HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        log.info("token : {} ", token);

        return (token.startsWith("Bearer "))
            ? token.substring(7,token.length()) : null;
    }
}
