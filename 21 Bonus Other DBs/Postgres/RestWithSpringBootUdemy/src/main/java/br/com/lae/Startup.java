package br.com.lae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.lae.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageConfig.class})
@EnableAutoConfiguration
@ComponentScan
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String result = bCryptPasswordEncoder.encode("root");
		System.out.println("My hash " + result);
	}
}
