package com.example.springdemo

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import kotlin.Throws
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl
import org.springframework.security.crypto.password.PasswordEncoder
import com.example.springdemo.Sha256PasswordEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.lang.Exception
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig (private val dataSource: DataSource) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val jdbcDao = JdbcDaoImpl()
        jdbcDao.setDataSource(dataSource)
        // disable user authorities
        jdbcDao.setEnableAuthorities(false)
        // enable groups
        jdbcDao.setEnableGroups(true)
        jdbcDao.setUsernameBasedPrimaryKey(false)
        jdbcDao.setUsersByUsernameQuery("""
            select user_name as username, password_hash as password, email_confirmed as enabled
            from public.aspnet_users
            where normalized_user_name = upper(?);
        """);
        jdbcDao.setGroupAuthoritiesByUsernameQuery("""
            select r.id, r.name as group_name, rc.claim_value as authority
            from public.aspnet_roles r
            inner join public.aspnet_role_claims rc
                on rc.role_id = r.id and rc.claim_type = 'AppPrivilege'
            inner join public.aspnet_user_roles ur on ur.role_id = r.id
            inner join public.aspnet_users u on ur.user_id = u.id
                and u.normalized_user_name = upper(?);
        """)
        return jdbcDao
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return Sha256PasswordEncoder()
    }

}
