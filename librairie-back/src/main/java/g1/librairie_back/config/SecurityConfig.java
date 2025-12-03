package g1.librairie_back.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {
    
    // Le SecurityFilterChain va nous permettre de configurer les accès, éventuellement le CSRF, politiques CORS générales, etc.
    @Bean // On bypass la config auto-configuration
    SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderFilter jwtFilter) throws Exception {
        // Configurer ici les accès généraux
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/compte/**").anonymous();
            auth.requestMatchers("/api/livre").hasAnyRole("CLIENT","ADMIN");
            auth.requestMatchers("/api/client").hasRole("ADMIN");

            // auth.requestMatchers("/api/matiere").hasAuthority("ROLE_USER");

            // auth.requestMatchers("/**").permitAll();
        });

        // Activer le formulaire de connexion
        http.formLogin(Customizer.withDefaults());

        // Activer l'authentification par HTTP Basic
        http.httpBasic(Customizer.withDefaults());

        // Désactiver la protection CSRF
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
        
        //politique CORS
        http.cors(cors -> {
            CorsConfigurationSource source = request -> {
                CorsConfiguration config = new CorsConfiguration();

                config.setAllowedHeaders(List.of("*"));
                config.setAllowedOrigins(List.of("*"));
                config.setAllowedMethods(List.of("*"));

                return config;
            };
            cors.configurationSource(source);
        });

        // Positionner le filter JwtHeaderFilter AVANT AuthenticationFilter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance(); // PAS BIEN

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println("\r\nMot de passe ===> " + passwordEncoder.encode("123456") + "\r\n");

        return passwordEncoder;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
