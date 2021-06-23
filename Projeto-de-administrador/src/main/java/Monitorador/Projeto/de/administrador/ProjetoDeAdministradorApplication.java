package Monitorador.Projeto.de.administrador;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAdminServer
@EnableAutoConfiguration
public class ProjetoDeAdministradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoDeAdministradorApplication.class, args);
	}

}
