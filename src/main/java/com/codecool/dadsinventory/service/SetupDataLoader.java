package com.codecool.dadsinventory.service;

import com.codecool.dadsinventory.model.Privilege;
import com.codecool.dadsinventory.model.Role;
import com.codecool.dadsinventory.model.User;
import com.codecool.dadsinventory.repository.PrivilegeRepository;
import com.codecool.dadsinventory.repository.RoleRepository;
import com.codecool.dadsinventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public SetupDataLoader(RoleRepository roleRepository, UserRepository userRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.privilegeRepository = privilegeRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(alreadySetup)
            return;

        Privilege momPrivilege = createPrivilegeIfNotFound("MOM_PRIVILEGE"); //creating privileges
        Privilege dadPrivilege = createPrivilegeIfNotFound("DAD_PRIVILEGE");

        List<Privilege> privileges = Arrays.asList(  //listing privileges
                momPrivilege, dadPrivilege);

        createRoleIfNotFound("ROLE_MOM", momPrivilege);
        createRoleIfNotFound("ROLE_DAD", dadPrivilege);

        Role momRole = roleRepository.findByName("ROLE_MOM");
        Role dadRole = roleRepository.findByName("ROLE_DAD");
        User mom = new User();
        mom.setUsername("mother");
        mom.setPassword(passwordEncoder.encode("bestmother"));
        mom.setRoles(Collections.singletonList(momRole));
        mom.setEnabled(true);
        userRepository.save(mom);

        User dad = new User();
        dad.setUsername("father");
        dad.setPassword(passwordEncoder.encode("bestfather"));
        dad.setRoles(Collections.singletonList(dadRole));
        dad.setEnabled(true);
        userRepository.save(dad);

        System.out.println("dad" + dad.getPassword());
        
        alreadySetup = true;
    }


    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if(privilege == null){
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Privilege privilege) {

        Role role = roleRepository.findByName(name);
        if(role == null){
            role = new Role(name);
            role.setPrivileges(Collections.singletonList(privilege));
            roleRepository.save(role);
        }
        return role;
    }


}
