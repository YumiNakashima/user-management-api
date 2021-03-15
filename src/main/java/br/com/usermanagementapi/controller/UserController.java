package br.com.usermanagementapi.controller;

import br.com.usermanagementapi.entity.User;
import br.com.usermanagementapi.mapper.UserMapper;
import br.com.usermanagementapi.model.reponse.UserResponse;
import br.com.usermanagementapi.model.request.UserRequest;
import br.com.usermanagementapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ApiOperation(value = "Create user")
    @PostMapping
    public ResponseEntity<UserResponse> postUser(@RequestBody UserRequest newUser) {
        User userToSave = mapper.toEntity(newUser);
        UserResponse savedUser = mapper.toDTO(userService.saveUser(userToSave));
        return ResponseEntity.created(URI.create("/" + savedUser.getId())).body(savedUser);
    }

    @ApiOperation(value = "Find user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long userId) {
        Optional<User> userById = userService.findUserById(userId);
        return userById
                .map(user ->
                        ok(mapper.toDTO(user))
                )
                .orElseGet(() -> notFound().build());
    }

    @ApiOperation(value = "Update user by id")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long userId, @RequestBody UserRequest userToUpdate) {
        User userToSave = mapper.toEntity(userId, userToUpdate);
        UserResponse updatedUser = mapper.toDTO(userService.updateUser(userId, userToSave));
        return ResponseEntity.ok(updatedUser);
    }

    @ApiOperation(value = "Delete user by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
