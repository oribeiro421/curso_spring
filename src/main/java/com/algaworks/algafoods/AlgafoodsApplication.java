package com.algaworks.algafoods;

import com.algaworks.algafoods.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodsApplication.class, args);
	}

}
