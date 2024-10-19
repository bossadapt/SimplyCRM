package SimplyCRM.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailService customUserDetailService;
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception{
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors(Customizer.withDefaults())
        .csrf(csrf -> csrf
            .disable()
        ).authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/auth/login").permitAll()
            .requestMatchers("/admin/**").hasRole("admin")
            .requestMatchers("/owner/**").hasRole("owner")
            .anyRequest().authenticated()
        ).formLogin(formLogin -> 
        formLogin.disable());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http){
        try {
            return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(customUserDetailService)
            .passwordEncoder(passwordEncoder()).and().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
