package com.canoerent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.query.users-query}")
    private String usersQuery;

    @Value("${spring.query.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManager)
            throws Exception {
        authenticationManager.jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.authorizeRequests()
                //.antMatchers("/index/**").permitAll()
                .antMatchers("/background/**").permitAll()
                //.antMatchers("/img/**").permitAll()
                .antMatchers("/",
                        //"/home",
                        "/register").permitAll()
                .antMatchers("/web/CanoeTrip",
                        "/web/canoeTripForm",
                        "/web/removeRentTripForm",
                        "/web/removeTripForm",
                        "/web/removeCanoeForm",
                        "/web/removeCanoeTripForm",
                        "/web/rentFunction",
                        "/web/tripFunction",
                        "/web/canoeFunction",
                        "/web/userFunction",
                        "/web/Rent",
                        "/web/Rents",
                        "/web/Canoe",
                        "/web/RentLast",
                        "/web/Trip",
                        "/web/Users",
                        "/web/removeForm",
                        "/web/rentForm",
                        "/web/roleForm",
                        "/web/tripForm",
                        "/web/canoeForm",
                        "/admin/**").hasRole("ADMIN")
                .antMatchers("/web/RentLast",

                        "/web/Rent",
                        "/web/rentForm").authenticated()   //.hasRole("USER") - gives privilage to unique endpoints
                .anyRequest().authenticated().and().formLogin().loginPage("/login")   //.defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll().and().logout()    //.logoutSuccessUrl("/");
                .permitAll();
        httpSecurity.exceptionHandling().accessDeniedPage("/403");
    }
}