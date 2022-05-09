package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Tags;
import isep.webtechno.placeholder.exceptions.TagsNotFoundException;
import isep.webtechno.placeholder.repositories.TagsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagsController {

    private final TagsRepository tagsRepository;

    TagsController(TagsRepository tagRepository) {
        this.tagsRepository = tagRepository;
    }

    @GetMapping("/Tags")
    List<Tags> all() {
        return tagsRepository.findAll();
    }

    @PostMapping("/Tags")
    Tags newTag(@RequestBody Tags newTag) {
        return tagsRepository.save(newTag);
    }

    @GetMapping("Tags/{id}")
    Tags one(@PathVariable Long id) {

        return tagsRepository.findById(id)
                .orElseThrow(() -> new TagsNotFoundException(id));
    }

    @PutMapping("/Tags/{id}")
    Tags replaceTag(@RequestBody Tags newTag, @PathVariable Long id) {

        return tagsRepository.findById(id)
                .map(tag -> {
                    tag.setLibelle(newTag.getLibelle());
                    tag.setOptionnel(newTag.getOptionnel());
                    tag.setType(newTag.getType());
                    return tagsRepository.save(tag);
                })
                .orElseGet(() -> {
                    newTag.setId(id);
                    return tagsRepository.save(newTag);
                });
    }

    @DeleteMapping("/Tags/{id}")
    void deleteTag(@PathVariable Long id) {
        tagsRepository.deleteById(id);
    }
}
