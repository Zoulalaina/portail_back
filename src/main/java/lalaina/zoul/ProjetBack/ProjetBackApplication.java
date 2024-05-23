package lalaina.zoul.ProjetBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProjetBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetBackApplication.class, args);
	}

}
