package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Images;
import isep.webtechno.placeholder.exceptions.ImagesNotFoundException;
import isep.webtechno.placeholder.repositories.ImagesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImagesController {
    private final ImagesRepository imagesRepository;

    ImagesController(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @GetMapping("/images")
    List<Images> all() {
        return imagesRepository.findAll();
    }

    @PostMapping("/images")
    Images newImage(@RequestBody Images newImage) {
        return imagesRepository.save(newImage);
    }

    @GetMapping("images/{id}")
    Images one(@PathVariable Long id) {

        return imagesRepository.findById(id)
                .orElseThrow(() -> new ImagesNotFoundException(id));
    }

    @PutMapping("/images/{id}")
    Images replaceImage(@RequestBody Images newImage, @PathVariable Long id) {

        return imagesRepository.findById(id)
                .map(image -> {
                    image.setFilename(newImage.getFilename());
                    image.setMaison(newImage.getMaison());
                    image.setCommentaires(newImage.getCommentaires());
                    image.setMessages(newImage.getMessages());
                    return imagesRepository.save(image);
                })
                .orElseGet(() -> {
                    newImage.setId(id);
                    return imagesRepository.save(newImage);
                });
    }

    @DeleteMapping("/images/{id}")
    void deleteImage(@PathVariable Long id) {
        imagesRepository.deleteById(id);
    }
}
