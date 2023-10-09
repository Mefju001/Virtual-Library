package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
}
