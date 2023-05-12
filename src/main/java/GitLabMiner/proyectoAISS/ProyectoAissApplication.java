package GitLabMiner.proyectoAISS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProyectoAissApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoAissApplication.class, args);
	}

	@Bean //es un objeto que va a tener que gestionar el framework
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}

