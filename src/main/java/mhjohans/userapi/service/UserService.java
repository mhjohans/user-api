package mhjohans.userapi.service;

import mhjohans.userapi.model.User;
import mhjohans.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        Set<Integer> existingIds = getExistingIds();
        for (int i = 1; i <= Integer.MAX_VALUE; i++) {
            if (!existingIds.contains(i)) {
                return i;
            }
        }
        throw new IllegalStateException("No available IDs.");
    }

    private HashSet<Integer> getExistingIds() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::id).collect(Collectors.toCollection(HashSet::new));
    }

    public Optional<User> updateUser(int id, User user) {
        if (getExistingIds().contains(id)) {
            return Optional.of(userRepository.save(new User(user, id)));
        }
        return Optional.empty();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
