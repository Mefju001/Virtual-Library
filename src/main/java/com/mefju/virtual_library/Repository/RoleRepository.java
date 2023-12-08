package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(value = "SELECT * FROM authorities WHERE Username=?",nativeQuery = true)
    Role findByUsername(String Username);
}
