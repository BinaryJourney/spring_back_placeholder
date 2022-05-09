package isep.webtechno.placeholder.configuration;

import isep.webtechno.placeholder.entities.Maisons;
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
            log.info("Preloading " + repository.save(new Maisons("Maison Un", "Ceci est la maison de test 1", "Liste de servies de test 1")));
            log.info("Preloading " + repository.save(new Maisons("Maison Deux", "Ceci est la maison de test 2", "Liste de servies de test 2")));
        };
    }
}
