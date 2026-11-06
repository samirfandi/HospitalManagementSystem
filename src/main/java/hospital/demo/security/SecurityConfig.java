package hospital.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import hospital.demo.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;

@AllArgsConstructor 
@Configuration 
@EnableWebSecurity 
//@EnableMethodSecurity (prePostEnabled = true)
public class SecurityConfig {


    
    private PasswordEncoder passwordEncoder;
    //uncomment to switch to UserDetailsService
    //private UserDetailsServiceImpl userDetailsServiceImpl;



    @Bean 
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
            User.withUsername("samir").password(passwordEncoder.encode("123")).roles("ADMIN", "USER").build(),
            User.withUsername("user").password(passwordEncoder.encode("123")).roles("USER").build()
        );
    }





    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

        //httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        //httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        //httpSecurity.formLogin(form -> {});

        httpSecurity.rememberMe(Customizer.withDefaults());
        httpSecurity.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/").permitAll());

        httpSecurity.authorizeHttpRequests(css -> css.requestMatchers("/webjars/**","/h2-console/**").permitAll());

          
        httpSecurity.authorizeHttpRequests(auth -> auth.
            requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasRole("USER")
            .anyRequest().authenticated()
        );

        // Autoriser les requêtes H2 sans CSRF
        httpSecurity.csrf(csrf ->
            csrf.ignoringRequestMatchers("/h2-console/**")
        );

        // Autoriser les frames H2
            httpSecurity.headers(headers ->
        headers.frameOptions(frame -> frame.sameOrigin())
        );
        
        httpSecurity.exceptionHandling(f -> f.accessDeniedPage("/notAuthorized"));

        //uncoment to switch to userDetailsService Implementation
        //httpSecurity.userDetailsService(userDetailsServiceImpl);

        return httpSecurity.build();
    }


    



}
