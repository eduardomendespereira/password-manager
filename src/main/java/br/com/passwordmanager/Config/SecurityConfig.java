package br.com.passwordmanager.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .httpBasic()
//                .and()
//                .authorizeHttpRequests()
//                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
//                .antMatchers(HttpMethod.PUT, "/api/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
                .csrf().disable();
//        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}