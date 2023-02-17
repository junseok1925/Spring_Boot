package com.example.sb.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

	private final UserService userService;

	@GetMapping("/signup")
	public String signup(UserForm userform) {
		return "signup_form";
	}

	@PostMapping("/signup")
	public String signup(@Valid UserForm userForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		if (!userForm.getPassword().equals(userForm.getPassword2())) {
			// 비밀번호와 비밀번호가 같이 않으면 에러메세지 호출
			bindingResult.rejectValue("password2", "pwNotEquals", "비밀번호가 다릅니다.");
			return "signup_form";
		}

		try {
			userService.create(userForm.getUsername(), userForm.getEmail(), userForm.getPassword());
		} catch (Exception e) {
			e.printStackTrace();

			bindingResult.reject("signupFail", "이미 가입된 아이디입니다.");

			return "signup_form";
		}

		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "login_form";
	}

}
