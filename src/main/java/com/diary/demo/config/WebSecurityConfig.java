package com.diary.demo.config;

import com.diary.demo.service.AuthProvider;
import com.diary.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthProvider authProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	PasswordEncoder passwordEncoder()
	{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				//Доступ разрешен всем пользователям
					.antMatchers("/registration").permitAll()
				//Все остальные страницы требуют аутентификации
					.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/login")
				//Перенарпавление на главную страницу после успешного входа
					.defaultSuccessUrl("/")
					.permitAll()
				.and()
					.logout()
					.permitAll();
	}

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
				.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	{
		auth.authenticationProvider(authProvider);
	}
}