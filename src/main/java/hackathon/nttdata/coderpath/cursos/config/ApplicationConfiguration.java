package hackathon.nttdata.coderpath.cursos.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@ConfigurationProperties
@RefreshScope
@Getter
@Setter
public class ApplicationConfiguration {

    @Value("${config.balanceador.test}")
    private String balanceadorTest;
    
    @Value("${config.base.endpoint}")
	private String urlExamenes;

	@Bean
	public WebClient registrarWebClient() {

		return WebClient.create(urlExamenes);

	}
}
