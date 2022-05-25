package com.example.demospringsecuritydemo.acccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	@Autowired AccountService accountService;

	@GetMapping("/account/{role}/{username}/{password}")
	public Account createACcount(@ModelAttribute Account account) {
		return accountService.createNew(account);
	}
}
