package br.com.usermanagementapi.service;

import br.com.usermanagementapi.entity.User;
import br.com.usermanagementapi.exception.UserNotFoundException;
import br.com.usermanagementapi.mapper.UserMapper;
import br.com.usermanagementapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public User saveUser(User userToSave) {
        return userRepository.save(userToSave);
    }

    public Optional<User> findUserById(Long userId){
        return userRepository.findById(userId);
    }

    public User updateUser(Long userId, User userToUpdate){
        Optional<User> userById = findUserById(userId);
        if (userById.isPresent()) {
            userMapper.updateEntity(userToUpdate, userById.get());
            return saveUser(userById.get());
        } else {
            throw new UserNotFoundException(format("User with id %d not found.", userId));
        }
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
