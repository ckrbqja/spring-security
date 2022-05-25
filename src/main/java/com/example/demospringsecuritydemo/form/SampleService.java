package com.example.demospringsecuritydemo.form;

import com.example.demospringsecuritydemo.acccount.Account;
import com.example.demospringsecuritydemo.acccount.AccountContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {
	public static void dashboard() {
		Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication1.getPrincipal();
		System.out.println("account = " + userDetails.getUsername());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Object credentials = authentication.getCredentials();
		boolean authenticated = authentication.isAuthenticated();

	}
}
