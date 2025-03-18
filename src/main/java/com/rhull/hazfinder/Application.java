package com.rhull.hazfinder;

import com.rhull.hazfinder.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application
{

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args)
	{
		ConfigurableApplicationContext context =  SpringApplication.run(Application.class, args);

		log.info("Application started successfully!");
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			Item item = new Item(1, "water", "water");
			log.info("Item: " + item);
		};
	}

}
