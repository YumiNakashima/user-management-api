package br.com.usermanagementapi.controller;


import br.com.usermanagementapi.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    public List<User> users = new ArrayList<>();

    public UserController(){
        users.addAll(List.of(new User(1, "Bruno", 32),
                new User(2, "Lary", 22),
                new User(3, "Will", 26),
                new User(4, "Thiago", 29)));
    }

    @GetMapping
    public List<User> getUsers() {
        return this.users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User dataUser = this.users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(dataUser);
    }

    @PostMapping
    public User postUser(@RequestBody User newUser){
        users.add(newUser);
        return newUser;
    }
}
