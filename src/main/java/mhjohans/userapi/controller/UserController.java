package mhjohans.userapi.controller;

import mhjohans.userapi.model.User;
import mhjohans.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getUsersByName(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            return userService.getUsersByName(name.get());
        } else {
            return userService.getAllUsers();
        }
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " does not exist"));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

}