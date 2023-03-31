package engine.security;

import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder encoder;

    private final UserService userService;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public WebSecurityConfig(BCryptPasswordEncoder encoder,
                             UserService userDetailsService, AuthenticationEntryPoint authenticationEntryPoint) {
        this.encoder = encoder;
        this.userService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, e) ->
                                                  resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/api/register", "/actuator/shutdown").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable().headers().frameOptions().disable();
    }
}
