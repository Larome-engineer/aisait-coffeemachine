package aisa.coffeemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoffeemachineApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoffeemachineApplication.class, args);
	}
}
