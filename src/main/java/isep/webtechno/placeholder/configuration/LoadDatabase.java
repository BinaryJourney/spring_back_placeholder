package isep.webtechno.placeholder.configuration;

import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.entities.Users;
import isep.webtechno.placeholder.repositories.MaisonsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MaisonsRepository repository) {

        return args -> {

        };
    }
}
