package uz.zako.zakoBackend.loader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.zako.zakoBackend.entity.Role;
import uz.zako.zakoBackend.entity.User;
import uz.zako.zakoBackend.repository.RoleRepository;
import uz.zako.zakoBackend.repository.UserRepository;

import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String init;
    @Override
    public void run(String... args) throws Exception {
        try {
            if (init.equals("create")) {
                Role roleAdmin = new Role();
                roleAdmin.setName("ROLE_ADMIN");
                roleAdmin.setId(1L);
                roleRepository.save(roleAdmin);

                Role roleUser = new Role();
                roleUser.setName("ROLE_USER");
                roleUser.setId(2L);
                roleRepository.save(roleUser);

                User user=new User();
                user.setUserName("admin");
                user.setPassword(passwordEncoder.encode("123"));
                user.setFullName("ADMIN");
                user.setPhoneNumber("+998971234567");
                user.setRoleList(Collections.singletonList(roleAdmin));
                user.setCreatedAt(new Date());

                userRepository.save(user);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
