package io.adworth.aip.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.adworth.aip.security.filter.AdworthAuthenticationFilter;
import io.adworth.aip.security.filter.AdworthLoginFilter;
import io.adworth.aip.security.entity.UserTable;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private BCryptPasswordEncoder passwordEncoder;

    public WebSecurityConfig(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/**").authenticated()
                .and()
                .addFilter(getAdworthLoginFilter())
                .addFilter(new AdworthAuthenticationFilter(authenticationManager()));
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// in-memory auth, adding Users
    	UserTable.addUser("root", "toor", "ADMIN");
    	UserTable.addUser("test", "pass", "USER");
    	for (int i = 0; i < UserTable.getSize(); i++) {
    		auth.inMemoryAuthentication()
    		.withUser(UserTable.getUser(i).getUsername())
    		.password(passwordEncoder.encode(UserTable.getUser(i).getPassword()))
    		.roles(UserTable.getUser(i).getRole());
    	}
    }
    
    public AdworthLoginFilter getAdworthLoginFilter() throws Exception{
        final AdworthLoginFilter filter = new AdworthLoginFilter(authenticationManager());
        return filter;
    }
}
