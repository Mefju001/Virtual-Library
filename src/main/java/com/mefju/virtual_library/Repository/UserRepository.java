package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE Username=?",nativeQuery = true)
    User findByUsername(String Username);

    @Query(value = "SELECT users.username,users.password,users.enabled,users.Imie,users.Nazwisko FROM users JOIN authorities  ON  authorities.username = users.username WHERE authorities.authority = 'ROLE_USER'", nativeQuery = true)
    List<User> listausers();
}
