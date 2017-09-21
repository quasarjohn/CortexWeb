package io.cortex.cortexweb.security;

import io.cortex.cortexweb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/",
                        "/index",
                        "/try-it",
                        "/documentation",
                        //allows us to get user info without redirecting the page
                        "/session/user-info",
                        //TODO change this -> test manual login
                        "/test/login/**",
                        "/use-case",
                        "/styles/**",
                        "/images/**",
                        "/scripts/**",
                        "/img/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/sign-in")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/sign-in?logout")
                .permitAll();
    }

    @Autowired
    DataSource cortexDataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(cortexDataSource).
                usersByUsernameQuery("select email, password, enabled from users where email = ?").
                authoritiesByUsernameQuery("select email, enabled from users where email = ?");
    }
}