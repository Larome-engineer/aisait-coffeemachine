package aisa.coffeemachine.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Coffee machine API",
                description = "That's api for manage coffee machine, inventory and do order",
                version = "1.0",
                contact = @Contact(
                        name = "Roman Lakusta",
                        email = "romanlacusta@gmail.com"
                )
        )
)
public class OpenApiConfig {
}
