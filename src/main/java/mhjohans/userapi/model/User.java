package mhjohans.userapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public record User(@Id int id, String name, String username, String email, Address address, String phone, String website, Company company) {

    /**
     * Create a new User from an existing User and a custom ID
     * 
     * @param user existing User object
     * @param id custom ID
     * 
     * @return a new User with the custom ID
     */
    public User(User user, int id) {
        this(id, user.name(), user.username(), user.email(), user.address(), user.phone(), user.website(), user.company());
    }
    
    public record Address(String street, String suite, String city, String zipcode, Geo geo) {
        public record Geo(String lat, String lng) {}
    }

    public record Company(String name, String catchPhrase, String bs) {}

}