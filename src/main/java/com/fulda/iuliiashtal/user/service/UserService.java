package com.fulda.iuliiashtal.user.service;

import com.fulda.iuliiashtal.user.entity.User;
import com.fulda.iuliiashtal.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UUID getUserId() {
        // Hardcoded user ID as specified
        return UUID.randomUUID();
    }

    public List<User> getUsers() {
        return repository.readFromJson();
    }

    public Optional<User> getUserById(UUID id) {
        return getUsers().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public List<User> getUsersByFullName(String firstName, String lastName) {
        return getUsers().stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(firstName)
                        && user.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public User create(String firstName, String lastName,
                       String email) {
        List<User> users = getUsers();
        User newUser = new User(UUID.randomUUID(), firstName, lastName, email, "defaultPassword");
        users.add(newUser);
        repository.writeToJson(users);
        return newUser;
    }
}
