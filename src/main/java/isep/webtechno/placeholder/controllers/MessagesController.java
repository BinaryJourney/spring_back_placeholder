package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Messages;
import isep.webtechno.placeholder.exceptions.MaisonsNotFoundException;
import isep.webtechno.placeholder.exceptions.MessagesNotFoundException;
import isep.webtechno.placeholder.repositories.MessagesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessagesController {

    private final MessagesRepository messagesRepository;

    MessagesController(MessagesRepository messageRepository) {
        this.messagesRepository = messageRepository;
    }

    @GetMapping("/messages")
    List<Messages> all() {
        return messagesRepository.findAll();
    }

    @PostMapping("/messages")
    Messages newMessage(@RequestBody Messages newMessage) {
        return messagesRepository.save(newMessage);
    }

    @GetMapping("messages/{id}")
    Messages one(@PathVariable Long id) {

        return messagesRepository.findById(id)
                .orElseThrow(() -> new MessagesNotFoundException(id));
    }

    @PutMapping("/messages/{id}")
    Messages replaceMaison(@RequestBody Messages newMessage, @PathVariable Long id) {

        return messagesRepository.findById(id)
                .map(message -> {
                    message.setTexte(newMessage.getTexte());
                    message.setTimestamp(newMessage.getTimestamp());
                    message.setSendingUser(newMessage.getSendingUser());
                    message.setReceivingUser(newMessage.getReceivingUser());
                    return messagesRepository.save(message);
                })
                .orElseGet(() -> {
                    newMessage.setId(id);
                    return messagesRepository.save(newMessage);
                });
    }

    @DeleteMapping("/messages/{id}")
    void deleteMaison(@PathVariable Long id) {
        messagesRepository.deleteById(id);
    }
}
