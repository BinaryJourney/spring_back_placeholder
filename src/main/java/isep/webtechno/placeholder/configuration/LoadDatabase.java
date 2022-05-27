package isep.webtechno.placeholder.configuration;

import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.entities.Tags;
import isep.webtechno.placeholder.entities.User;
import isep.webtechno.placeholder.repositories.MaisonsRepository;
import isep.webtechno.placeholder.repositories.TagsRepository;
import isep.webtechno.placeholder.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    User maxAdmin = new User("max", "dev", "max@gmail.com", "root", "ADMIN");

//    @Bean
//    CommandLineRunner initDatabase(UserRepository userRepository) {
//
//        return args -> {
//            log.info("Preloading " + userRepository.save(maxAdmin));
//        };
//    }

//    @Bean
//    CommandLineRunner initDatabase2(TagsRepository tagsRepository) {
//
//        return args -> {
//            log.info("Preloading " + tagsRepository.save(new Tags("Salle de bain", "Sèche-cheveux")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Salle de bain", "Produits de nettoyage")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Salle de bain", "Eau chaude")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chambre et linge", "Lave-linge")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chambre et linge", "Equipement de base (serviettes, draps, savon, papier-toilette)")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chambre et linge", "Cintres")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chambre et linge", "Draps")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chambre et linge", "Fer à repasser")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chambre et linge", "Etendoir à linge")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chambre et linge", "Dressing et/ou commode")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Divertissement", "Television")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Divertissement", "Console de jeu")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Famille", "Lit Parapluie")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chauffage et climatisation", "Climatisation")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chauffage et climatisation", "Chauffage")));
//            log.info("Preloading " + tagsRepository.save(new Tags("Chauffage et climatisation", "Ventilateurs portables")));
//
//
//        };
//    }
}
