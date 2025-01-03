package finalProject;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertyConfig {
    @Bean(name = "secret")
    public PropertiesFactoryBean propertiesFactoryBean() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        ClassPathResource resource = new ClassPathResource("application-secret.properties");
        propertiesFactoryBean.setLocation(resource);

        return propertiesFactoryBean;
    }
}
