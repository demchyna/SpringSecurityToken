package com.softserve.academy.config;

import com.softserve.academy.security.AuthenticationAccessDeniedHandler;
import com.softserve.academy.security.AuthenticationTokenFilter;
import com.softserve.academy.security.AuthenticationTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.softserve.academy")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySources({
        @PropertySource("classpath:security.properties"),
        @PropertySource("classpath:urls.properties")
})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${urls.loginUrl}")
    private String LOGIN_URL;

    @Value("${urls.secureUrl}")
    private String SECURE_URL;

    @Autowired private AbstractAuthenticationProcessingFilter tokenAuthenticationFilter;
    @Autowired private AuthenticationTokenManager authenticationTokenManager;

    @Autowired private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .rememberMe().disable()
                .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);
    }

    @Bean
    public AbstractAuthenticationProcessingFilter getTokenAuthenticationFilter() {
        AuthenticationTokenFilter filter = new AuthenticationTokenFilter(SECURE_URL);
        filter.setAuthenticationManager(authenticationTokenManager);
        return filter;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}