package com.mefju.virtual_library.Service;

import com.mefju.virtual_library.Entity.Role;
import com.mefju.virtual_library.Entity.User;
import com.mefju.virtual_library.Repository.RoleRepository;
import com.mefju.virtual_library.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public void SetUserAndRole(UserRepository userRepository,RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Transactional
    public void AddUser(User user)
    {
        userRepository.save(user);
    }
    @Transactional
    public List<User> ListUsers()
    {
        return userRepository.listausers();
    }
    @Transactional
    public boolean Exist(User user)
    {
        return userRepository.existsById(user.getUsername());
    }
    @Transactional
    public void AddRole(Role role)
    {
        roleRepository.save(role);
    }

    @Transactional
    public String changeUserPassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(username);
        oldPassword = "{noop}"+oldPassword;
        if (user == null) {
            return "Użytkownik nie istnieje";
        }

        if (!user.getPassword().equals(oldPassword)) {
            return "Stare hasło jest nieprawidłowe";
        }

        user.setPassword("{noop}"+newPassword);
        userRepository.save(user);

        return "success";
    }

    @Transactional
    public void deleteUser(String username) {
        User userRepo = userRepository.findByUsername(username);
        Role roleRepo = roleRepository.findByUsername(username);
        if (userRepo == null) {
            //return "Użytkownik nie istnieje";
        }
        else if (roleRepo == null) {
            //return "rola nie istnieje";
        }
        else{
            roleRepository.delete(roleRepo);
            userRepository.delete(userRepo);
        }

    }
}

