package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> find(Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> update(Long id, User newUser) {
        Mono<User> user = userRepository.findById(id);
        newUser.setId(id);

        return userRepository.save(newUser);

    }

    public Mono delete(Long id) {
        return userRepository.deleteUserById(id);
    }
    // END
}
