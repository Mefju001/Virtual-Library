package com.mefju.virtual_library.Service;

import com.mefju.virtual_library.Entity.User;
import com.mefju.virtual_library.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void SaveUSR(User user)
    {
        userRepository.save(user);
    }
}
