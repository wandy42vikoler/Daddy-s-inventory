package com.codecool.dadsinventory.repository;

import com.codecool.dadsinventory.model.Role;
import com.codecool.dadsinventory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role_mom);
}

