package com.codecool.dadsinventory.repository;

import com.codecool.dadsinventory.model.Privilege;
import com.codecool.dadsinventory.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
