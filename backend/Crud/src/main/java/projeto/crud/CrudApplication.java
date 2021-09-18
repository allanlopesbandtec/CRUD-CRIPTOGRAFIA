package projeto.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableFeignClients
@EnableSwagger2
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	//Para usar arquivo .war necessário --> extends SpringBootServletInitializer
	//Override do médodo configure

	//	@Override
	//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	//		return builder.sources(CrudApplication.class);
	//	}
}
