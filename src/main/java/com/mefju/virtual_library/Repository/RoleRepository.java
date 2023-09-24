package com.mefju.virtual_library.Repository;

import com.mefju.virtual_library.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
