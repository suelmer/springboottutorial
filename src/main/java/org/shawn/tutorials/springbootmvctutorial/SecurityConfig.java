package org.shawn.tutorials.springbootmvctutorial;

import org.springframework.context.annotation.Configuration;
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
        web.ignoring().antMatchers("/VAADIN/**");
        web.ignoring().antMatchers("/vaadinServlet/UIDL/**");
        web.ignoring().antMatchers("/vaadinlogin/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().
                formLogin().loginPage("/vaadinlogin").failureUrl("/vaadinlogin?error").loginProcessingUrl("/login").
                defaultSuccessUrl("/home").usernameParameter("username").passwordParameter("password").
                permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/vaadinlogin?loggedOut").
                permitAll().and().csrf().disable();
    }
}
