package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
