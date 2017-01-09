package br.ufes.ceunes.projeta.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests().anyRequest().permitAll().and().cors().disable().csrf().disable();
//		http.authorizeRequests().antMatchers("/cadastro/**","/listar/**").hasRole("rh").anyRequest().permitAll()
//		.and().formLogin().loginPage("/login").permitAll()
//		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.and().csrf().disable();
	}
	
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/bootstrap/fonts/**")
		.and().ignoring().antMatchers("/bootstrap/css/**")
		.and().ignoring().antMatchers("/bootstrap/js/**")
		.and().ignoring().antMatchers("/bootstrap/img/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("projeta").password("123").roles("rh")
		.and().withUser("andre").password("123").roles();
		
	}
}
