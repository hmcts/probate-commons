package uk.gov.hmcts.probate.commons.config;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class PebbleTemplateConfiguration {
    @Bean
    public PebbleEngine pebbleEngine(Loader<?> pebbleLoader) {
        return new PebbleEngine.Builder()
                .strictVariables(true)
                .loader(pebbleLoader)
                .build();
    }

    @Bean
    Loader<?> pebbleLoader() {
        final ClasspathLoader classpathLoader = new ClasspathLoader();
        classpathLoader.setCharset(StandardCharsets.UTF_8.name());
        classpathLoader.setSuffix(".peb");
        return classpathLoader;
    }
}
