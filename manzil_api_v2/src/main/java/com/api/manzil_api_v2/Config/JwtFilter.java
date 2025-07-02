package com.api.manzil_api_v2.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired private JwtService jwtService;
    @Autowired private UserDetailsService uds;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws ServletException, IOException {

        final String h = req.getHeader("Authorization");
        String email = null, token = null;
        if (h != null && h.startsWith("Bearer ")) {
            token = h.substring(7); email = jwtService.extractEmail(token);
        }
        if (email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails ud = uds.loadUserByUsername(email);
            if (jwtService.validateToken(token, ud)) {
                var auth = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(req, res);
    }
}
