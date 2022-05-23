package isep.webtechno.placeholder.controllers;

import isep.webtechno.placeholder.entities.User_bak;
import isep.webtechno.placeholder.exceptions.UsersNotFoundException;
import isep.webtechno.placeholder.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    private final UserRepository usersRepository;

    UsersController(UserRepository userRepository) {
        this.usersRepository = userRepository;
    }

    @GetMapping("/Users")
    List<User_bak> all() {
        return usersRepository.findAll();
    }

    @PostMapping("/Users")
    User_bak newUser(@RequestBody User_bak newUser) {
        return usersRepository.save(newUser);
    }

    @GetMapping("Users/{id}")
    User_bak one(@PathVariable Long id) {

        return usersRepository.findById(id)
                .orElseThrow(() -> new UsersNotFoundException(id));
    }

    @PutMapping("/Users/{id}")
    User_bak replaceUser(@RequestBody User_bak newUser, @PathVariable Long id) {

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
