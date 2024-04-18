package repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mhjohans.userapi.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

  /**
   * Finds all User objects with a name that exactly matches the input parameter
   * 
   * @param name The name to search for
   * 
   * @return A list of User objects with a name that exactly matches the search parameter
   */
  public List<User> findByName(String name);

}
