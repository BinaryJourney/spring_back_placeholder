package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Commentaires;
import isep.webtechno.placeholder.exceptions.CommentairesNotFoundException;
import isep.webtechno.placeholder.exceptions.MaisonsNotFoundException;
import isep.webtechno.placeholder.repositories.CommentairesRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class CommentairesController {
    private final CommentairesRepository commentairesRepository;

    CommentairesController(CommentairesRepository commentairesRepository) {
        this.commentairesRepository = commentairesRepository;
    }

    @GetMapping("/commentaires")
    List<Commentaires> all() {
        return commentairesRepository.findAll();
    }

    @PostMapping("/commentaires")
    Commentaires newCommentaire(@RequestBody Commentaires newCommentaire) {
        return commentairesRepository.save(newCommentaire);
    }

    @GetMapping("commentaires/{id}")
    Commentaires one(@PathVariable Long id) {

        return commentairesRepository.findById(id)
                .orElseThrow(() -> new CommentairesNotFoundException(id));
    }

    @PutMapping("/commentaires/{id}")
    Commentaires replaceCommentaire(@RequestBody Commentaires newCommentaire, @PathVariable Long id) {

        return commentairesRepository.findById(id)
                .map(commentaire -> {
                    commentaire.setNote(newCommentaire.getNote());
                    commentaire.setText(newCommentaire.getText());
                    commentaire.setTimestamp(newCommentaire.getTimestamp());
                    commentaire.setUsers(newCommentaire.getUsers());
                    commentaire.setMaison(newCommentaire.getMaison());
                    commentaire.setTitre(newCommentaire.getTitre());
                    return commentairesRepository.save(commentaire);
                })
                .orElseGet(() -> {
                    newCommentaire.setId(id);
                    return commentairesRepository.save(newCommentaire);
                });
    }

    @DeleteMapping("/commentaires/{id}")
    void deleteCommentaire(@PathVariable Long id) {
        commentairesRepository.deleteById(id);
    }
}
