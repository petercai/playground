package cai.peter.spring.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath*:application-context.xml"})
@ComponentScan({"cai.peter.spring.rest.*"})
public class SprintRestTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintRestTestApplication.class, args);
	}

}
