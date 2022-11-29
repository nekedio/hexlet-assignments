package exercise.repository;

import exercise.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

// BEGIN
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    Mono<Void> deleteUserById(Long id);

    Mono<User> findUserById(Long id);
}
// END
