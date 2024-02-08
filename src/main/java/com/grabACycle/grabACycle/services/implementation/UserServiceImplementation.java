package com.grabACycle.grabACycle.services.implementation;

import com.grabACycle.grabACycle.dao.UserRepository;
import com.grabACycle.grabACycle.entity.User;
import com.grabACycle.grabACycle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user)
    {
        User tempUser = new User();

        tempUser.setUserId(user.getUserId());
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(user.getPassword());
        tempUser.setDob(user.getDob());

        return userRepository.save(tempUser);
    }

    @Override
    public List<User> fetchUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> fetchUserById(int userId)
    {
        return userRepository.findById(userId);
    }

    @Override
    public User updateUser(User user)
    {
        User tempUser = new User();

        tempUser.setUserId(user.getUserId());
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(user.getPassword());
        tempUser.setDob(user.getDob());

        return userRepository.save(tempUser);
    }

    @Override
    public User updateUserById(int userId, User user)
    {
        User tempUser = userRepository.findById(userId).get();

        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(user.getPassword());
        tempUser.setDob(user.getDob());

        return userRepository.save(tempUser);
    }

    @Override
    public void deleteUser(int userId)
    {
        userRepository.deleteById(userId);
    }
}
