package com.grabACycle.grabACycle.services.implementation;

import com.grabACycle.grabACycle.dao.UserRepository;

import com.grabACycle.grabACycle.entity.Role;
import com.grabACycle.grabACycle.entity.User;
import com.grabACycle.grabACycle.services.UserService;
import com.grabACycle.grabACycle.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"user"})
public class UserServiceImplementation implements UserService {


    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    @CachePut(cacheNames = "user")
    public User createUser(User user)
    {
        User tempUser = new User();

        tempUser.setId(user.getId());
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setEmail(user.getEmail());
        tempUser.setPassword(user.getPassword());
        tempUser.setDob(user.getDob());

        return userRepository.save(tempUser);
    }

    @Override
    @Cacheable(cacheNames = "user")
    public List<User> fetchUsers()
    {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "user",unless="#result == null")
    public Optional<User> fetchUserById(int userId)
    {
        return userRepository.findById(userId);
    }

    @Override
    @CachePut(cacheNames = "user")
    public User updateUser(User user)
    {
        User tempUser = new User();

        tempUser.setId(user.getId());
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setEmail(user.getEmail());
        tempUser.setPassword(user.getPassword());
        tempUser.setDob(user.getDob());

        return userRepository.save(tempUser);
    }

    @Override
    public User updateUserById(int userId, User user)
    {
        User tempUser = userRepository.findById(userId).get();

        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setEmail(user.getEmail());
        tempUser.setPassword(user.getPassword());
        tempUser.setDob(user.getDob());

        return userRepository.save(tempUser);
    }

    @Override
    @CacheEvict(cacheNames = "user", allEntries = true)
    public void deleteUser(int userId)
    {
        userRepository.deleteById(userId);
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user=new User(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getDob(),Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);

        // if user is not found, throws exception
        if(user==null)
            throw new UsernameNotFoundException("Invalid username and password");

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

}
