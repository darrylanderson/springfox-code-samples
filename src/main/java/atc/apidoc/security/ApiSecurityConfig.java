package atc.apidoc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class ApiSecurityConfig
      extends WebSecurityConfigurerAdapter
{

    @Override
    public void configure( final WebSecurity web )
          throws Exception
    {
        web.ignoring().antMatchers( "/v2/api-docs", "/index.html" );
    }

    @Override
    protected void configure( final HttpSecurity http )
          throws Exception
    {
        // Http basic auth
        http.sessionManagement()
            .sessionCreationPolicy( STATELESS )
            .and()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable();
    }
}
