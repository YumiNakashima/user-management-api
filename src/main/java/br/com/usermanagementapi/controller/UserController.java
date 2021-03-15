package br.com.usermanagementapi.controller;

import br.com.usermanagementapi.dto.UserDTO;
import br.com.usermanagementapi.entity.User;
import br.com.usermanagementapi.mapper.UserMapper;
import br.com.usermanagementapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@RequestBody UserDTO newUser) {
        User userToSave = mapper.toEntity(newUser);
        UserDTO savedUser = mapper.toDTO(userService.saveUser(userToSave));
        return ResponseEntity.created(URI.create("/" + savedUser.getId())).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long userId) {
        Optional<User> userById = userService.findUserById(userId);
        return userById
                .map(user ->
                        ok(mapper.toDTO(user))
                )
                .orElseGet(() -> notFound().build());
    }
}
