package mhjohans.userapi.repository;

import mhjohans.userapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    /**
     * Finds all Users with a name that exactly matches the given name parameter
     *
     * @param name The name to search for
     * @return A list of Users with a name that exactly matches the search parameter
     */
    List<User> findByName(String name);

}
