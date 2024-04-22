package com.nicksy.security;

import com.nicksy.security.entity.Authority;
import com.nicksy.security.entity.UserPrincipal;
import com.nicksy.security.repository.AuthorityRepo;
import com.nicksy.security.repository.UserPrincipalRepo;
import com.nicksy.security.util.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {
	@Autowired
	AuthorityRepo authorityRepo;
	@Autowired
	UserPrincipalRepo userPrincipalRepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Authority userAuth = Authority.builder()
				.authority(RoleEnum.ROLE_USER).build();
		if (authorityRepo.findAll().isEmpty()) {
			authorityRepo.save(userAuth);
		}

		if (userPrincipalRepo.findAll().isEmpty()) {
			userPrincipalRepo.save(
					new UserPrincipal(
							"USER", passwordEncoder.encode("user"),
							Collections.singletonList(userAuth))
			);
		}
	}
}
