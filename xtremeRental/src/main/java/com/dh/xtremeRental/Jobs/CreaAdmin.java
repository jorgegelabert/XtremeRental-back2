package com.dh.xtremeRental.Jobs;

import com.dh.xtremeRental.Auth.AuthResponse;
import com.dh.xtremeRental.User.Role;
import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.dto.UserDto;
import com.dh.xtremeRental.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public  class CreaAdmin {

    private final PasswordEncoder passwordEncoder=null;
    private final UserRepository userRepository=null;

    public void creaUsuarioAdmin(UserDto userAdmin){


    User user = User.builder()
            .id(userAdmin.getId())
            .username(userAdmin.getUsername())
            .password(passwordEncoder.encode( userAdmin.getPassword()))
            .nombre(userAdmin.getNombre())
            .apellido(userAdmin.getApellido())
            .email(userAdmin.getEmail())
            .role(Role.USER)
            .build();

    userRepository.save(user);
    }
}
