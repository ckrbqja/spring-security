package com.example.demospringsecuritydemo.form;

import com.example.demospringsecuritydemo.acccount.AccountContext;
import com.example.demospringsecuritydemo.acccount.AccountRepository;
import com.example.demospringsecuritydemo.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Objects;
import java.util.concurrent.Callable;

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


	@GetMapping("/user") public String user(Model model, Principal principal ) {
		model.addAttribute("message", "hello user " + principal.getName());
		return "user";
	}

	@GetMapping("/async-handler") @ResponseBody public Callable<String> asyncHandler() {

		SecurityLogger.log("MVC");

		return () -> {
			SecurityLogger.log("Callable");
			return "Async Handler";
		};
	}
	@GetMapping("/async-service") @ResponseBody public String asyncService() {

		SecurityLogger.log("MVC start");
		sampleService.asyncService();
		SecurityLogger.log("MVC after");
		return "Async Service";
	}
}