package lalaina.zoul.ProjetBack.config;

import lalaina.zoul.ProjetBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig {
@Autowired
    private final JwtFilter jwtFilter;

    private final UserDetailsService userDetailsService;
private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityAppConfig(JwtFilter jwtFilter, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return
                httpSecurity
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(
                                authorize ->
                                        authorize.requestMatchers("api/v1/users/inscription").permitAll()
                                                .requestMatchers("api/v1/users/connexion").permitAll()
                                                .requestMatchers("api/v1/users/deconnexion").permitAll()
                                                .requestMatchers(GET,"api/v1/university").permitAll()
                                                .requestMatchers(GET,"api/v1/university/all").hasAuthority("ROLE_ADMIN")
                                                .requestMatchers(GET,"api/v1/university/{id}").permitAll()
                                                .requestMatchers(GET,"api/v1/etablissement").permitAll()
                                                .requestMatchers(GET,"api/v1/etablissement/{id}").permitAll()
                                                .requestMatchers(GET,"api/v1/etablissement/id/{id}").permitAll()
                                                .requestMatchers(GET,"api/v1/mention").permitAll()
                                                .requestMatchers(GET,"api/v1/mention/all").hasAuthority("ROLE_ADMIN")
                                                .requestMatchers(GET,"api/v1/mention/{id}").permitAll()
                                                .requestMatchers(GET,"api/v1/mention/id/{id}").permitAll()
                                                .requestMatchers(GET,"api/v1/parcours").permitAll()
                                                .requestMatchers(GET,"api/v1/parcours/all").hasAuthority("ROLE_ADMIN")
                                                .requestMatchers(GET,"api/v1/parcours/{id}").permitAll()
                                                .requestMatchers(GET,"api/v1/parcours/id/{id}").permitAll()
                                                .requestMatchers(GET,"api/v1/avis/{id}").permitAll()
                                                .requestMatchers(POST,"api/v1/avis").hasAuthority("ROLE_UTILISATEUR")
                                                .anyRequest().hasAuthority("ROLE_ADMIN")

                        )
                        .sessionManagement(httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }


}

