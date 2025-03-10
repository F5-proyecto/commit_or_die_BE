package com.f5.commit_or_die;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.f5.commit_or_die.model")
public class CommitOrDieApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitOrDieApplication.class, args);
	}

}
