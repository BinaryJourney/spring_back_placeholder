package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.Users;
import isep.webtechno.placeholder.exceptions.UsersNotFoundException;
import isep.webtechno.placeholder.repositories.UsersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    private final UsersRepository usersRepository;

    UsersController(UsersRepository userRepository) {
        this.usersRepository = userRepository;
    }

    @GetMapping("/Users")
    List<Users> all() {
        return usersRepository.findAll();
    }

    @PostMapping("/Users")
    Users newUser(@RequestBody Users newUser) {
        return usersRepository.save(newUser);
    }

    @GetMapping("Users/{id}")
    Users one(@PathVariable Long id) {

        return usersRepository.findById(id)
                .orElseThrow(() -> new UsersNotFoundException(id));
    }

    @PutMapping("/Users/{id}")
    Users replaceUser(@RequestBody Users newUser, @PathVariable Long id) {

        return usersRepository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setNom(newUser.getNom());
                    user.setPrenom(newUser.getPrenom());
                    user.setRole(newUser.getRole());
                    return usersRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return usersRepository.save(newUser);
                });
    }

    @DeleteMapping("/Users/{id}")
    void deleteUser(@PathVariable Long id) {
        usersRepository.deleteById(id);
    }
}
