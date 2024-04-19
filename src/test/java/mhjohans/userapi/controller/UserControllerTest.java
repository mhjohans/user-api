package mhjohans.userapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mhjohans.userapi.model.User;
import mhjohans.userapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> users;

    @BeforeEach
    void setUp() {
        // Load test data from resources/data/users.json JSON file into a list of User objects
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User[] usersArray = objectMapper.readValue(new File("src/test/resources/data/users.json"), User[].class);
            users = Arrays.asList(usersArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(users);
        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(10))).
                andExpect(jsonPath("$[6].name").value("Kurtis Weissnat"));
    }

    @Test
    public void testGetUserById() throws Exception {
        Mockito.when(userService.getUserById(1)).thenReturn(users.get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.email").value("Sincere@april.biz"));
    }

    @Test
    public void testGetUsersByName() throws Exception {
        Mockito.when(userService.getUsersByName("Ervin Howell")).thenReturn(List.of(users.get(1)));
        mockMvc.perform(MockMvcRequestBuilders.get("/users?name=Ervin Howell").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(1))).
                andExpect(jsonPath("$[0].name").value("Ervin Howell"));
    }

    @Test
    public void testGetUsersByNameNotFound() throws Exception {
        Mockito.when(userService.getUsersByName("John Doe")).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get("/users?name=John Doe").contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = users.get(2);
        String content = new ObjectMapper().writeValueAsString(user);
        Mockito.when(userService.createUser(user)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/users").content(content).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated()).
                andExpect(jsonPath("$.username").value("Samantha"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = users.get(3);
        String content = new ObjectMapper().writeValueAsString(user);
        Mockito.when(userService.updateUser(4, user)).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.put("/users/4").content(content).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.address.street").value("Hoeger Mall"));
    }

    @Test
    public void testUpdateUserNotFound() throws Exception {
        User user = users.get(3);
        String content = new ObjectMapper().writeValueAsString(user);
        Mockito.when(userService.updateUser(11, user)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.put("/users/11").content(content).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteUser(5);
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/5").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

}