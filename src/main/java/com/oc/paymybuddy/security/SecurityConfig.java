package com.oc.paymybuddy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      PasswordEncoder passwordEncoder = passwordEncoder();
       /*
        String encodedPWD = passwordEncoder.encode("adem98");
        System.out.println(encodedPWD);
        auth.inMemoryAuthentication().withUser("userName").password(encodedPWD).roles("USER");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT mail, password, actif FROM client WHERE email = ?");
                        */
     auth.inMemoryAuthentication().withUser("springuser")
             .password(passwordEncoder().encode("spring456")).roles("USER")
             .and().withUser("springadmin").password(passwordEncoder().encode("admin890")).roles("ADMIN","USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin();
        /*http.formLogin();
        http.authorizeRequests().anyRequest().authenticated();*/
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
