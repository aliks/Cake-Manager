package com.waracle.cakes.cakemanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.model.Cake;
import com.waracle.cakes.cakemanager.com.warcle.cakes.cakemanager.service.CakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@SpringBootApplication
public class CakeManagerApplication {

	private static final Logger logger
			= LoggerFactory.getLogger(CakeManagerApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(CakeManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CakeService cakeService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Cake>> ref = new TypeReference<List<Cake>>() {};
			InputStream in = TypeReference.class.getResourceAsStream("/cakes.json");
			try {
				List<Cake> cakes = mapper.readValue(in, ref);
				cakeService.save(cakes);
				logger.info("Cakes data saved!");
			} catch (IOException e) {
				logger.error("Unable to save cakes: " + e.getMessage());
			}
		};
	}
}
