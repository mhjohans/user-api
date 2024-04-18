package mhjohans.userapi.service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mhjohans.userapi.model.User;
import repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return sortUsers(userRepository.findAll());
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsersByName(String name) {
        return sortUsers(userRepository.findByName(name));
    }

    private List<User> sortUsers(List<User> users) {
        users.sort(Comparator.comparingInt(User::id));
        return users;
    }

    public User createUser(User user) {
        int id = generateNewId();
        return userRepository.save(new User(user, id));
    }

    private int generateNewId() {
        List<User> users = userRepository.findAll();
        Set<Integer> existingIds = users.stream().map(User::id).collect(Collectors.toCollection(HashSet::new));
        for (int i = 1; i <= Integer.MAX_VALUE; i++) {
            if (!existingIds.contains(i)) {
                return i;
            }
        }
        throw new IllegalStateException("No available IDs.");
    }
    
    public User updateUser(int id, User user) {
        return userRepository.save(new User(user, id));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
