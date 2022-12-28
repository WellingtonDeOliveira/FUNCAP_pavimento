package br.com.api.usuarios.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Em produção tirar o csrf disable, pois isso tira a segurançã da aplicação
        http.csrf().disable()
            .authorizeRequests().antMatchers("/cadastrar", "/role").permitAll().and()
            .httpBasic();
    }
    
}
