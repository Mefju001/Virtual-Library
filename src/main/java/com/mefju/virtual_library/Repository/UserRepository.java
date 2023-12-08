package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM users WHERE Username=?",nativeQuery = true)
    User findByUsername(String Username);
}
