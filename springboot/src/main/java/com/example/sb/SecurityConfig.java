package com.example.sb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //설정용클래스이다~
@EnableWebSecurity //사용자가 요청을하면 security를 거쳐서 넘어가도록 설정
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	//기본설정은 인증되지않은 사용자는 막음
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http.authorizeHttpRequests() //권한에 관련된 코드
			.requestMatchers(new AntPathRequestMatcher("/**")) //어떤 형태로 접근해도 모든페이지에 접속가능하게 해준다
			.permitAll()
			.and() //보안코큰 설정
				.csrf()
				.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
			.and()
				.headers()
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
									 XFrameOptionsHeaderWriter
									.XFrameOptionsMode.SAMEORIGIN))
				
				//로그인
			.and()
				.formLogin()
				.loginPage("/user/login")
				.defaultSuccessUrl("/")
				
				//로그아웃
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true);
				
		
		return http.build(); //모든 설정을 합친정보를 리턴
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		
		return authenticationConfiguration.getAuthenticationManager();
	}
}
