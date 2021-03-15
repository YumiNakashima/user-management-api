package br.com.usermanagementapi.service;

import br.com.usermanagementapi.entity.User;
import br.com.usermanagementapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User userToSave) {
        return userRepository.save(userToSave);
    }
}
