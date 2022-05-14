package com.example.botheroku.component;

import com.example.botheroku.Constatns;
import com.example.botheroku.entity.Role;
import com.example.botheroku.entity.Rule;
import com.example.botheroku.entity.User;
import com.example.botheroku.entity.enums.RoleName;
import com.example.botheroku.repository.RoleRepository;
import com.example.botheroku.repository.RuleRepository;
import com.example.botheroku.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            ruleRepository.save(new Rule("0","23","1","59","122004314", Constatns.SEND_HOUR, Constatns.SEND_MINUTE));

            Role sAdmin = roleRepository.save(new Role(
                    RoleName.SUPER_ADMIN
            ));

            Role admin = roleRepository.save(new Role(
                    RoleName.ADMIN
            ));

            userRepository.save(new User(
                    "Super Admin firstname",
                    "Super Admin lastname",
                    "sadmin",
                    passwordEncoder.encode("123"),
                    Collections.singleton(sAdmin)
            ));

            userRepository.save(new User(
                    "Admin firstname",
                    "Admin lastname",
                    "admin",
                    passwordEncoder.encode("456"),
                    Collections.singleton(admin)
            ));

        }
    }

}
