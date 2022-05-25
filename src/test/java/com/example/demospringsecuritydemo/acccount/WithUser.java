package com.example.demospringsecuritydemo.acccount;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "rbqja", password = "123", roles = "ADMIN")
public @interface WithUser {
	String[] roles() default {"USER"};
}
