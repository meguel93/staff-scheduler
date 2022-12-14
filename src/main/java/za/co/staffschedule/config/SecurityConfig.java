package za.co.staffschedule.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(@Autowired AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/v1/staff").permitAll()
                .antMatchers(HttpMethod.GET, "/v1/staff").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/v1/staff").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/staff").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "v1/schedules").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "v1/schedules").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "v1/schedules").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "v1/schedules").hasAnyRole()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
