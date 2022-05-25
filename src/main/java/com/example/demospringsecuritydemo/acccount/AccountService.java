package com.example.demospringsecuritydemo.acccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

	@Autowired AccountRepository accountRepository;
	@Autowired PasswordEncoder passwordEncoder;

	@Override public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		Account account = accountRepository.findByUsername(username);

		if (Objects.isNull(account))
			throw new UsernameNotFoundException(username);

		return User.builder()
				.username(account.getUsername())
				.password(account.getPassword())
				.roles(account.getRole())
			   .build();
	}

	public Account createNew(final Account account) {
		account.encodePassword(account,passwordEncoder);
		this.accountRepository.save(account);
		return account;
	}
}
