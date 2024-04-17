package mhjohans.userapi;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

  Optional<User> findById(Integer id);
  
  void deleteById(Integer id);

  List<User> findByName(String name);

}
