package isep.webtechno.placeholder.configuration;

import isep.webtechno.placeholder.entities.User;
import isep.webtechno.placeholder.repositories.MaisonsRepository;
import isep.webtechno.placeholder.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new User("max", "dev", "max@gmail.com", "root", "ADMIN")));
        };
    }
}
