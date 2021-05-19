package com.example.springdemo.security;

import com.example.springdemo.security.JwtTokenFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final DataSource dataSource;
    private final JwtTokenFilter jwtTokenFilter;
    
    public SecurityConfig(DataSource dataSource, JwtTokenFilter jwtTokenFilter) {
        this.dataSource = dataSource;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // enable cors;
        http.cors();
        // disable csrf;
        http.csrf().disable();
        // set session to stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // set unahthorized forbidden requests exception handler;
        http.exceptionHandling()
            .authenticationEntryPoint(
                (req, res, ex) -> {
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                }
            ).accessDeniedHandler(
                (req, res, ex) -> {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, ex.getMessage());
                }
            );
        // authorize requests
//        http.authorizeRequests()
//            .antMatchers("/", "/api/account/**", "/api/users/**").permitAll()
//            .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // todo: need user repo;
        // auth.userDetailsService(username -> )
    }
    
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /*
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        var jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        // disable user authorities
        jdbcDao.setEnableAuthorities(false);
        // enable groups
        jdbcDao.setEnableGroups(true);
        jdbcDao.setUsernameBasedPrimaryKey(false);
        jdbcDao.setUsersByUsernameQuery(
            "select user_name as username, password_hash as password, email_confirmed as enabled\n" + 
            "from public.aspnet_users\n" +
            "where normalized_user_name = upper(?);"
        );
        jdbcDao.setGroupAuthoritiesByUsernameQuery(
            "select r.id, r.name as group_name, rc.claim_value as authority\n" +
            "from public.aspnet_roles r\n" +
            "inner join public.aspnet_role_claims rc\n" +
            "    on rc.role_id = r.id and rc.claim_type = 'AppPrivilege'\n" +
            "inner join public.aspnet_user_roles ur on ur.role_id = r.id\n" +
            "inner join public.aspnet_users u on ur.user_id = u.id\n" +
            "    and u.normalized_user_name = upper(?);"
        );
        return jdbcDao;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Sha256PasswordEncoder();
    }
    */

    // Expose authentication manager bean
    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
