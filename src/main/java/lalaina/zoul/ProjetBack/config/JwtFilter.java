package lalaina.zoul.ProjetBack.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lalaina.zoul.ProjetBack.model.Jwt;
import lalaina.zoul.ProjetBack.service.JwtService;
import lalaina.zoul.ProjetBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserService utilisateurService;
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        Jwt tokenDansLaBDD = null;
        String username = null;
        boolean isTokenExpired = true;

        // Bearer eyJhbGciOiJIUzI1NiJ9.eyJub20iOiJBY2hpbGxlIE1CT1VHVUVORyIsImVtYWlsIjoiYWNoaWxsZS5tYm91Z3VlbmdAY2hpbGxvLnRlY2gifQ.zDuRKmkonHdUez-CLWKIk5Jdq9vFSUgxtgdU1H2216U
        final String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            tokenDansLaBDD = jwtService.tokenByValue(token);
            isTokenExpired = jwtService.isTokenExpired(token);
            username = jwtService.extractUsername(token);
        }

        if(!isTokenExpired  && tokenDansLaBDD.getUser().getEmail().equals(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = utilisateurService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
    }

