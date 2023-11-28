package com.dh.xtremeRental.Jobs;

import com.dh.xtremeRental.User.Role;
import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.repository.UserRepository;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@Component
public class CreaAdmin implements CommandLineRunner {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public void run (String... args) throws Exception {

        if (userRepository.count() == 0){

            User user = User.builder()
                    .id(1)
                    .username("admin")
                    .password(passwordEncoder.encode("admin1234"))
                    .nombre("Administrador")
                    .apellido("Perez")
                    .email("administradorperez@gmail.com")
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user);
    }}
}
