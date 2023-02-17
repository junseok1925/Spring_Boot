package com.example.sb.user;

import lombok.Getter;

@Getter
public enum UserRole {
	//이용자의 권한을 설정
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String value;
	
	UserRole(String value){
		this.value = value;
	}
	
}
