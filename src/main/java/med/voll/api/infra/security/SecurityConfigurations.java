package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers(CorsConfiguration.ALL).permitAll()
                        .requestMatchers(HttpMethod.GET, "/hello").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios").hasAnyRole("*")
                        .requestMatchers(HttpMethod.GET, "/medicos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pacientes").permitAll()
                        .requestMatchers("css/**").permitAll()
                        .requestMatchers("img/**").permitAll()
                        .requestMatchers("js/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("index.html").permitAll()
                        .anyRequest().authenticated()
                );
        http.cors(Customizer.withDefaults());

        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
