package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Maison;
import isep.webtechno.placeholder.exceptions.MaisonNotFoundException;
import isep.webtechno.placeholder.repositories.MaisonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaisonController {

    private final MaisonRepository maisonRepository;

    MaisonController(MaisonRepository maisonRepository) {
        this.maisonRepository = maisonRepository;
    }

    @GetMapping("/maisons")
    List<Maison> all() {
        return maisonRepository.findAll();
    }

    @PostMapping("/maisons")
    Maison newMaison(@RequestBody Maison newMaison) {
        return maisonRepository.save(newMaison);
    }

    @GetMapping("maison/{id}")
    Maison one(@PathVariable Long id) {

        return maisonRepository.findById(id)
                .orElseThrow(() -> new MaisonNotFoundException(id));
    }

    @PutMapping("/maisons/{id}")
    Maison replaceMaison(@RequestBody Maison newMaison, @PathVariable Long id) {

        return maisonRepository.findById(id)
                .map(maison -> {
                    maison.setTitre(newMaison.getTitre());
                    maison.setDescription(newMaison.getDescription());
                    maison.setDateDispoDebut(newMaison.getDateDispoDebut());
                    maison.setDateDispoFin(newMaison.getDateDispoFin());
                    maison.setListeServices(newMaison.getListeServices());
                    return maisonRepository.save(maison);
                })
                .orElseGet(() -> {
                    newMaison.setId(id);
                    return maisonRepository.save(newMaison);
                });
    }

    @DeleteMapping("/maisons/{id}")
    void deleteMaison(@PathVariable Long id) {
        maisonRepository.deleteById(id);
    }
}
