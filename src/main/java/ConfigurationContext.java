import model.Dictionary;
import model.DictionaryFileStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Represents spring configuration.
 */
@Configuration
@ComponentScan({"base", "model", "view"})
@PropertySource("application.properties")
public class ConfigurationContext {
    @Bean
    @Value("${ALL_WORDS_PATH}")
    Dictionary allWordsDictionary(String path) {
        return new DictionaryFileStorage(path);
    }

    @Bean
    @Value("${HIDDEN_WORDS_PATH}")
    Dictionary hiddenWordsDictionary(String path) {
        return new DictionaryFileStorage(path);
    }
}
