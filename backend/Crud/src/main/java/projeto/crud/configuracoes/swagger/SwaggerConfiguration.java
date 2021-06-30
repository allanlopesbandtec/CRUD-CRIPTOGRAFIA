package projeto.crud.configuracoes.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import projeto.crud.model.Usuario;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class SwaggerConfiguration {
    
    @Value("${crud.swagger.path}")
    private String swaggerPath;

    public Docket crudApp() {

        ParameterBuilder parametroAuthorization = new ParameterBuilder();

        parametroAuthorization.name("Authorization")
                .description("Header para token Jwt")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> listaParametros = new ArrayList<Parameter>();

        listaParametros.add(parametroAuthorization.build());

        return new Docket(DocumentationType.SWAGGER_2).host(swaggerPath)
                .groupName("All")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.projeto.crud"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(Usuario.class)
                .enableUrlTemplating(true)
                .globalOperationParameters(listaParametros);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Crud API")
                .description("Crud com criptografia")
                .termsOfServiceUrl("http://localhost:8080")
                .license("")
                .licenseUrl("http://localhost:8080")
                .version("2.0")
                .build();
    }
}
