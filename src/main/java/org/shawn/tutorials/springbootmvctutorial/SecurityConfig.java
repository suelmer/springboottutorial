package org.shawn.tutorials.springbootmvctutorial;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Shawn on 2015/11/21.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().
                formLogin().loginPage("/myLogin").failureUrl("/myLogin?error").loginProcessingUrl("/login").
                defaultSuccessUrl("/home").usernameParameter("username").passwordParameter("password").
                permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/myLogin?loggedOut").
                permitAll().and().csrf().disable();
    }
}
