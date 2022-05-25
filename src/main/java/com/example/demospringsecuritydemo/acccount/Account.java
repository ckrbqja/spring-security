package com.example.demospringsecuritydemo.acccount;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class  Account {

	@Id @GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String username;

	private String password;
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public void encodePassword(final Account account, final PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(account.getPassword());
	}
}
