package edu.fra.uas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.fra.uas.controller.BeanController;
import edu.fra.uas.service.MessageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BeanExampleApplication {

	private static final Logger log = LoggerFactory.getLogger(BeanExampleApplication.class);

	@Autowired
	private BeanController beanController;

	public static void main(String[] args) {
		SpringApplication.run(BeanExampleApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		CommandLineRunner action = new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				log.debug(beanController.putMessage("Hallo Welt!"));
				log.debug(beanController.putMessage("--> OOOHOOO <--"));
			}
		};
		return action;
	}
}
