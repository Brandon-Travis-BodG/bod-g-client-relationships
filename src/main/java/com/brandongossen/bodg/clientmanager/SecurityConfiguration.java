package com.brandongossen.bodg.clientmanager;


import com.brandongossen.bodg.clientmanager.services.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
        extends WebSecurityConfigurerAdapter
{

    @Autowired
    private UserDetailsLoader userDetails;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/") // user's home page, it can be any URL (right now have it as regular homepage, but this will change)
                .permitAll() // Anyone can go to the login page
                .and()
                .authorizeRequests()
                .antMatchers("/", "/logout") // anyone can see the home and logout page
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                .and()
                .authorizeRequests()
//                .antMatchers() // only authenticated users can look at certain pages (This will be determined once proper controllers and views are created).
//                .authenticated()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }
}
