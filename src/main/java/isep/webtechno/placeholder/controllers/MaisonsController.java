package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Maisons;
import isep.webtechno.placeholder.exceptions.MaisonsNotFoundException;
import isep.webtechno.placeholder.repositories.MaisonsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaisonsController {

    private final MaisonsRepository maisonsRepository;

    MaisonsController(MaisonsRepository maisonRepository) {
        this.maisonsRepository = maisonRepository;
    }

    @GetMapping("/maisons")
    List<Maisons> all() {
        return maisonsRepository.findAll();
    }

    @PostMapping("/maisons")
    Maisons newMaison(@RequestBody Maisons newMaison) {
        return maisonsRepository.save(newMaison);
    }

    @GetMapping("maisons/{id}")
    Maisons one(@PathVariable Long id) {

        return maisonsRepository.findById(id)
                .orElseThrow(() -> new MaisonsNotFoundException(id));
    }

    @PutMapping("/maisons/{id}")
    Maisons replaceMaison(@RequestBody Maisons newMaison, @PathVariable Long id) {

        return maisonsRepository.findById(id)
                .map(maison -> {
                    maison.setTitre(newMaison.getTitre());
                    maison.setDescription(newMaison.getDescription());
                    maison.setDateDispoDebut(newMaison.getDateDispoDebut());
                    maison.setDateDispoFin(newMaison.getDateDispoFin());
                    maison.setListeServices(newMaison.getListeServices());
                    return maisonsRepository.save(maison);
                })
                .orElseGet(() -> {
                    newMaison.setId(id);
                    return maisonsRepository.save(newMaison);
                });
    }

    @DeleteMapping("/maisons/{id}")
    void deleteMaison(@PathVariable Long id) {
        maisonsRepository.deleteById(id);
    }
}
