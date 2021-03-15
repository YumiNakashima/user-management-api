package br.com.usermanagementapi.service;

import br.com.usermanagementapi.entity.User;
import br.com.usermanagementapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User userToSave) {
        return userRepository.save(userToSave);
    }

    public Optional<User> findUserById(Long userId){
        return userRepository.findById(userId);
    }
}
