package uz.zako.zakoBackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.zako.zakoBackend.security.JwtConfig;
import uz.zako.zakoBackend.security.JwtEntryPoint;
import uz.zako.zakoBackend.security.JwtTokenProvider;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources", "/swagger-resources/**", "/v2/**", "/csrf").permitAll()
//                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/user/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/api/admin/** ").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .exceptionHandling().authenticationEntryPoint(entryPoint).and()
                .apply(new JwtConfig(jwtTokenProvider));
//                .and()
//                .httpBasic();
//                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
