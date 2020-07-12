package com.superhonor.shipment.configuration;

import com.superhonor.shipment.filter.JwtAuthenticationFilter;
import com.superhonor.shipment.service.impl.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author liuweidong
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Configuration
    public static class MySecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private DatabaseUserDetailsService userDetailsService;

        @Autowired
        @Qualifier("authenticationSuccessHandler")
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        @Qualifier("authenticationFailHandler")
        private AuthenticationFailureHandler failHandler;

        @Autowired
        @Qualifier("authenticationEntryPointImpl")
        private AuthenticationEntryPoint entryPoint;

        public JwtAuthenticationFilter getJwtAuthenticationFilter() {
            return new JwtAuthenticationFilter();
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and().csrf().disable()
                    .authorizeRequests()
                        .antMatchers("/v2/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginProcessingUrl("/api/login")
                        .successHandler(successHandler)
                        .failureHandler(failHandler).and()
                    .exceptionHandling().authenticationEntryPoint(entryPoint);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        @Override
        public void configure(WebSecurity web) {
            //allow Swagger URL to be accessed without authentication
            web.ignoring().antMatchers("/v2/api-docs",//swagger api json
                    "/swagger-resources/configuration/ui",//用来获取支持的动作
                    "/swagger-resources",//用来获取api-docs的URI
                    "/swagger-resources/configuration/security",//安全选项
                    "/swagger-ui.html");
        }
    }
}
