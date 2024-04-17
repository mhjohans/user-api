package mhjohans.userapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public User createUser(User user) {
        // TODO: Check if user already exists
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User user) {
        // TODO: Implement user update
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

}
