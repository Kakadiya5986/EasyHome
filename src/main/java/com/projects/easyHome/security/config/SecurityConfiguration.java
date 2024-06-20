package com.projects.easyHome.security.config;

import com.projects.easyHome.service.AppUserService;
import com.projects.easyHome.service.InMemoryUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity // Assuming necessary
public class SecurityConfiguration {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsService(bCryptPasswordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Consider using CSRF protection
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/api/v*/registration/**").permitAll();auth.anyRequest().authenticated();})
                .formLogin(formlogin->formlogin.defaultSuccessUrl("/index.html", true));
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
}





////@Configuration
//@AllArgsConstructor
////@EnableWebSecurity
//public class WebsecurityConfiguration{
//
//    private final AppUserService appUserService;
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//
//    protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable()
//                    .authorizeRequests()
//                            .antMatchers("/api/v*/registration/**")
//                            .permitAll()
//                            .anyRequest()
//                            .authenticated().and()
//                            .formLogin()
//            .defaultSuccessUrl("/index.html",true);
//
//    }
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        daoAuthenticationProvider.setUserDetailsService(appUserService);
//        return daoAuthenticationProvider;
//    }
//}
