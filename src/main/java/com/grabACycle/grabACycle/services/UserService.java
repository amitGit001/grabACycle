package com.grabACycle.grabACycle.services;

import com.grabACycle.grabACycle.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    List<User> fetchUsers();

    Optional<User> fetchUserById(int userId);

    User updateUser(User user);

    User updateUserById(int userId, User user);

    void deleteUser(int userId);

}
