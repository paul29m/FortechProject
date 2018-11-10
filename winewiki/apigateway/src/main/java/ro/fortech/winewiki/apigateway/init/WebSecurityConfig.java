package ro.fortech.winewiki.apigateway.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import ro.fortech.winewiki.apigateway.security.CorsFilter;
import ro.fortech.winewiki.apigateway.security.RestAuthenticationEntryPoint;
import ro.fortech.winewiki.apigateway.security.RestAuthenticationFailureHandler;
import ro.fortech.winewiki.apigateway.security.RestAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("ro.fortech.winewiki.apigateway.security")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RestAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private RestAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private UserDetailsService apiGatewayUserDetailsService;

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return apiGatewayUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().addFilterBefore(corsFilter(), ChannelProcessingFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                //TODO: REMOVE UNNECESSARY PERMITS
                .and().authorizeRequests().antMatchers("/api/person/create").permitAll()
                .antMatchers("/api/person/*").hasAnyRole("ADMIN")
                .antMatchers("/api/winetype/*").hasAnyRole("ADMIN")
                .antMatchers("/api/wines/*").hasAnyRole("ADMIN")
                .and().formLogin()
                .loginPage("/login").successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                .and().logout().logoutSuccessUrl("/login");
    }
}
