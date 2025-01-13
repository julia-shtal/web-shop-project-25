package com.fulda.iuliiashtal.user.service;

import com.fulda.iuliiashtal.user.entity.User;
import com.fulda.iuliiashtal.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The {@code UserService} class provides business logic for managing user-related operations
 * in the application. It interacts with the {@link UserRepository} to handle data persistence
 * and retrieval.
 *
 * <p>The primary responsibilities of this class include:
 * <ul>
 *     <li>Retrieving a hardcoded user ID</li>
 *     <li>Fetching a list of users from the repository</li>
 *     <li>Filtering users by ID or full name</li>
 *     <li>Creating and persisting a new user</li>
 * </ul>
 *
 * @see UserRepository
 * @see User
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    /**
     * Retrieves a hardcoded user ID.
     *
     * <p>This method currently generates and returns a random {@link UUID}.
     * It can be extended to fetch the authenticated user's ID or a user-specific ID in the future.
     *
     * @return a randomly generated {@link UUID}
     */
    public UUID getUserId() {
        return UUID.randomUUID();
    }

    /**
     * Retrieves a list of all users.
     *
     * <p>This method fetches the user data from the {@link UserRepository}.
     *
     * @return a list of {@link User} objects
     */
    public List<User> getUsers() {
        return repository.readFromJson();
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user
     * @return an {@link Optional} containing the {@link User} if found, or empty if not
     */
    public Optional<User> getUserById(UUID id) {
        return getUsers().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    /**
     * Retrieves users by their full name.
     *
     * <p>This method filters users by their first and last names, ignoring case.
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @return a list of {@link User} objects matching the provided full name
     */
    public List<User> getUsersByFullName(String firstName, String lastName) {
        return getUsers().stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(firstName)
                        && user.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new user and persists it to the repository.
     *
     * <p>This method generates a new {@link User} object with the provided details, assigns a default
     * password, and saves the updated user list to the repository.
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param email the email address of the user
     * @return the newly created {@link User}
     */
    public User create(String firstName, String lastName, String email) {
        List<User> users = getUsers();
        User newUser = new User(UUID.randomUUID(), firstName, lastName, email, "defaultPassword");
        users.add(newUser);
        repository.writeToJson(users);
        return newUser;
    }
}
