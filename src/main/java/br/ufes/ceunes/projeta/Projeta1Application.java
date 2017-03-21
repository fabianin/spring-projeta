package br.ufes.ceunes.projeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Projeta1Application {
	
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Projeta1Application.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(Projeta1Application.class, args);
	}
}
