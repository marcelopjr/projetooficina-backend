package com.projeto.oficina.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	private static final String USUARIO_POR_LOGIN = "SELECT email, senha , 'true' as enable FROM usuarios WHERE email=?";

	private static final String USUARIO_AUTHORITY = "SELECT email, tipo FROM usuarios WHERE email=?";

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery(USUARIO_POR_LOGIN).authoritiesByUsernameQuery(USUARIO_AUTHORITY);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/**").permitAll()
				.antMatchers(HttpMethod.POST, "/usuarios/cadastro").permitAll()
				.antMatchers(HttpMethod.PUT, "/usuarios/ativaremail").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.antMatchers(HttpMethod.POST, "/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and().cors().and().csrf().disable()
				.addFilter(jwtBasicAuthenticationFilter()).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}

	@Bean
	public JWTBasicAuthenticationFilter jwtBasicAuthenticationFilter() throws Exception {
		return new JWTBasicAuthenticationFilter(authenticationManager());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins("http://localhost:3000/")
				.allowedMethods("*").allowCredentials(true);
	}

}
