package com.example.demospringsecuritydemo.acccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	AccountService accountService;

	@Test
	@WithAnonymousUser
	public void index_anoymous() throws Exception{
		mockMvc.perform(get("/").with(anonymous()))
				.andDo(print())
				.andExpect(status().isOk());
	}


//	user("cs").roles("USER") mocking
	@WithUser
	@Test public void index_user() throws Exception{
		mockMvc.perform(get("/").with(user("rbqja").password("123").roles("USER")))
				.andDo(print())
				.andExpect(status().isOk());
	}
	@WithUser
	@Test public void admin_user() throws Exception{
		mockMvc.perform(get("/admin").with(user("rbqja").password("123").roles("USER")))
				.andDo(print())
				.andExpect(status().isForbidden());
	}

	@WithUser(roles = "ADMIN")
	@Test public void admin_admin() throws Exception{
		mockMvc.perform(get("/admin").with(user("rbqja").password("123").roles("ADMIN")))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void  login() throws Exception{

		String username = "kyubeom";
		String password = "123";

		final Account account = createUSer(username, password);

		accountService.createNew(account);
		mockMvc.perform(formLogin().user(username).password(password))
				.andExpect(authenticated());

	}
	@Test
	@Transactional
	public void login_fail() throws Exception{

		String username = "kyubeom";
		String password = "123";

		final Account account = createUSer(username, password);

		accountService.createNew(account);
		mockMvc.perform(formLogin().user(username).password(password + "1"))
				.andExpect(unauthenticated());

	}

	private Account createUSer(final String username, final String password) {
		final Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setRole("USER");
		return account;
	}

}