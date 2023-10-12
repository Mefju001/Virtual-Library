package com.mefju.virtual_library.Service;

import com.mefju.virtual_library.Entity.Role;
import com.mefju.virtual_library.Entity.User;
import com.mefju.virtual_library.Repository.RoleRepository;
import com.mefju.virtual_library.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void AddUser(User user)
    {
        userRepository.save(user);
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
}
