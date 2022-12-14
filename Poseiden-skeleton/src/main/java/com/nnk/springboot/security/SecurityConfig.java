package com.nnk.springboot.security;
import com.nnk.springboot.service.UserDetailsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;

/**
 * Filters Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsConfigService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    // http.csrf => On desactive l'authentification Spring Boot
    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable()
                /*
                 * Access setup
                 */
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").authenticated()
                .antMatchers(HttpMethod.POST,"/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").authenticated()
                .antMatchers(HttpMethod.PUT,"/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").authenticated()
                .antMatchers(HttpMethod.DELETE,"/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").authenticated()
//                    .hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/user/**")
                .hasAnyAuthority("ADMIN")
                /*
                 * Login Setup
                 */
                .and()
                .formLogin()
                .loginPage("/app/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/bidList/list")
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/bidList/list")
                /*
                 * Logout
                 */
                .and()
                .logout()
                .logoutUrl("/app-logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/app/error")
                /*
                 * Authentification de base for Configure methods
                 */
                .and()
                .httpBasic();

    }

    //    @Override
  /*  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider())
                .inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("Azerty@"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("Azerty@"))
                .roles("ADMIN");
    }*/


}

