package g1.librairie_back.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import g1.librairie_back.dao.IDAOCompte;
import g1.librairie_back.model.Client;
import g1.librairie_back.model.Compte;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {
    @Autowired
    private IDAOCompte daoCompte;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null){
            String token = header.substring(7);

            Optional<String> optEmail = JwtUtils.validateAndGetSubjet(token);

            if(optEmail.isPresent()){
                Compte compte = this.daoCompte.findByEmail(optEmail.get()).orElseThrow();

                List<GrantedAuthority> authorities = new ArrayList<>();

                if(compte instanceof Client) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
                } else {
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                }

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(optEmail.get(), null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }

        filterChain.doFilter(request,response);

    }

}
