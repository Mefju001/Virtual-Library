package com.mefju.virtual_library.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Security {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.authorizeHttpRequests(configurer->
                        configurer
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/Login").permitAll()
                                .requestMatchers("/Logowanie.css").permitAll()
                                .requestMatchers("/img/**").permitAll()
                                .requestMatchers("/Menu").hasAnyRole("ADMIN")
                                .requestMatchers("/logout").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/Add",
                                        "/ShowFormForUpdate",
                                        "/ShowFormForAdd").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .exceptionHandling(configurer->
                        configurer
                                .accessDeniedPage("/odmowa_dostepu"))
                .formLogin(form->
                        form
                                .loginPage("/Login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/Menu",true)
                                .permitAll()
                )
                .logout(LogoutConfigurer->LogoutConfigurer
                        .permitAll()
                        .logoutSuccessUrl("/")
                );

        return httpSecurity.build();
    }
}
