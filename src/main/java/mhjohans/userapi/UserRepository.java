package mhjohans.userapi;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends MongoRepository<User, Integer> {

  List<User> findByName(@Param("name") String name);

}
