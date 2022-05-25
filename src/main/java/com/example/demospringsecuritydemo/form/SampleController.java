package com.example.demospringsecuritydemo.form;

import com.example.demospringsecuritydemo.acccount.AccountContext;
import com.example.demospringsecuritydemo.acccount.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
public class SampleController {

	@Autowired SampleService sampleService;
	@Autowired AccountRepository accountRepository;

	@GetMapping("/") public String index(Model model, Principal principal) {

		System.out.println("principal = " + principal);
		if(Objects.isNull(principal))
			model.addAttribute("message", "Hello Spring Security");
		else
			model.addAttribute("message", "hello " + principal.getName());

		return "index";
	}
	@GetMapping("/info") public String info(Model model) {
		model.addAttribute("message", "info");
		return "info";
	}
	@GetMapping("/dashboard") public String dashboard(Model model, Principal principal) {
		model.addAttribute("message", "dashboard1");
		AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
		SampleService.dashboard();

		return "dashboard";
	}
	@GetMapping("/admin") public String admin(Model model, Principal principal ) {
		model.addAttribute("message", "hello admin " + principal.getName());
		return "admin";
	}
}