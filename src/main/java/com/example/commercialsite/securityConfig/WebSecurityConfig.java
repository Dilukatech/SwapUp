package com.example.commercialsite.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    // original code
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                .authorizeRequests().antMatchers("api/v1/favorites/{userId}","api/v1/favorites/{userId}/items/{itemId}","/api/v1/favorites/add-favorites",
                        "/api/v1/item/save-item","/api/v1/item/get-items",
                        "/api/v1/user/update-profile-pic/{id}","/payment/payment-complete/{userId}",
                        "/api/v1/user/get-user/{id}","/payment/create-checkout-session",
                        "/api/v1/user/login","/api/v1/customer/get-help-request/{id}",
                        "/api/v1/user/verification/**","/payment/get-subscribed-user/{userId}",
                        "/api/v1/admin/register-staff","/api/v1/admin/hold-user","/api/v1/admin/get-all-users",
                        "/api/v1/customer/request-token","/api/v1/customer/saveRequestToken","/api/v1/customer/help-request",
                        "/api/v1/quality-checker/accept-request-token","/api/v1/quality-checker/get-request-token",
                        "/api/v1/quality-checker/reject-request-token","/api/v1/user/item-detail/{itemId}",
                        "/api/v1/customer/AllRequestToken","/payment/create-payment-intent","/payment/confirm-payment",
                        "/api/v1/customer/RequestTokenFromCustomer","/api/v1/customer/AllRequestTokenFromCustomer","/api/v1/user/register-customer",
                        "/api/v1/help-assistant/CheckHelpRequestFromHelpAssistant","/api/v1/help-assistant/GetHelpRequestFromHelpAssistant/{helpRequestId}",
                        "/api/v1/help-assistant/GetHelpRequestsFromStatus/**","/api/v1/help-assistant/GetHelpRequests","/api/v1/admin/get-all-membership-by-active-state/{status}",
                        "/api/v1/admin/get-all-item-in-remaining-store/{status}")
                        .permitAll()

                .antMatchers(HttpHeaders.ALLOW).permitAll()

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    // end of original code





    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
