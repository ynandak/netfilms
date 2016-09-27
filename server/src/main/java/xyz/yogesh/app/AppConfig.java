package xyz.yogesh.app;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
        }
    
    @Bean
    public MappingJackson2HttpMessageConverter converter() {
        ObjectMapper om = new ObjectMapper();
        om.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        MappingJackson2HttpMessageConverter mc = new MappingJackson2HttpMessageConverter(om);
        return mc;
    }
}