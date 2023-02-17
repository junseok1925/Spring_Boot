package com.example.sb.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) {
		
		SiteUser user = new SiteUser();
		
		user.setUsername(username);
		user.setEmail(email);
		
		//비밀번호를 그대로 DB에 저장시 유출되면 큰일나기때문에
		//받은 비밀번호를 인코딩으로 암호화해서 DB에 저장
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		
		userRepository.save(user);
		
		return user;
	}
	
	//username을 받아서 username에 해당하는 레코드를 뽑아와서 SiteUser형태인 객체로 리턴
	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = userRepository.findByusername(username);
		
		return siteUser.get();
	}
}
