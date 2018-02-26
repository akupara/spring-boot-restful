package in.xroc.dev.demo;

import com.github.shihyuho.jackson.databind.DynamicFilterMixIn;
import com.github.shihyuho.jackson.databind.DynamicFilterProvider;
import com.github.shihyuho.jackson.databind.DynamicFilterResponseBodyAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestfulApplication.class, args);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer addJacksonDynamicFilter() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.mixIn(Object.class, DynamicFilterMixIn.class);
            jacksonObjectMapperBuilder.filters(new DynamicFilterProvider());
        };
    }

    @Bean
    public DynamicFilterResponseBodyAdvice dynamicFilterResponseBodyAdvice() {
        return new DynamicFilterResponseBodyAdvice();
    }
}
