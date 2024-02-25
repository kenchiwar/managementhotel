package com.demo.configurations;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.demo.services.AccountService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private AccountService accountService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.cors(cor -> cor.disable()).csrf(csf -> csf.disable()).authorizeHttpRequests(auth -> {
			auth.requestMatchers("/admin/**").hasAnyRole("SUPER_ADMIN")
					.requestMatchers("/dashboard/**", "/requestsupport")
					.hasAnyRole("SUPER_ADMIN", "ADMIN", "BUSINESS")
					.requestMatchers("/assets/**", "/account/login")
					.permitAll().requestMatchers("/**").permitAll();
					})
					.formLogin(formLogin -> {
					formLogin.loginPage("/account/login")
					.loginProcessingUrl("/account/process-login")
					.usernameParameter("email")
					.passwordParameter("password")
					// .defaultSuccessUrl("/account/welcome")
					.successHandler(new AuthenticationSuccessHandler() {
						@Override
						public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
								Authentication authentication) throws IOException, ServletException {
							Map<String, String> urls = new HashMap<String, String>();
							urls.put("ROLE_SUPPER_ADMIN", "/admin");
							urls.put("ROLE_ADMIN", "/admin");
							urls.put("ROLE_BUSINESS", "/admin");
							urls.put("ROLE_USER", "/account/profile");
							String url = "";
							for (GrantedAuthority role : authentication.getAuthorities()) {
								if (urls.containsKey(role.getAuthority())) {
									url = urls.get(role.getAuthority());
									break;
								}
							}
							response.sendRedirect(url);
						}
					}).failureUrl("/account/login?error");
		}).logout(logout -> {
			logout.logoutUrl("/account/logout").logoutSuccessUrl("/account/login");
		}).exceptionHandling(ex -> {
			ex.accessDeniedPage("/account/accessDenied");
		}).build();
	}

	@Autowired
	public void configGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(accountService);
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
