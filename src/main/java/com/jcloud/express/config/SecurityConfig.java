package com.jcloud.express.config;

import com.jcloud.express.service.user.JcloudUserDetailsService;
import com.jcloud.express.util.CustomLogoutHandler;
import com.jcloud.express.util.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private JcloudUserDetailsService userDetailsService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    LoginHandler loginHandler;

    @Autowired
    CustomLogoutHandler logoutHandler;

    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/css/**", "/js/**", "/webjars/**", "fonts/**", "/images/**", "/error/**","/api/**").permitAll()
                .antMatchers("/dashboard").authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .successHandler(loginHandler)
                .and()
                .logout().addLogoutHandler(logoutHandler)
                .logoutSuccessUrl("/login?logout")
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login");;

    }

}
