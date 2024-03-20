package com.texoit.goldenraspberryawardsapi;

import com.texoit.goldenraspberryawardsapi.adapters.out.base.repository.RefreshableJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(repositoryBaseClass = RefreshableJpaRepositoryImpl.class)
public class GoldenRaspberryAwardsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldenRaspberryAwardsApiApplication.class, args);
	}

}
