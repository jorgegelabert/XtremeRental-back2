package com.dh.xtremeRental.Auth;



import com.dh.xtremeRental.Jwt.JwtService;
import com.dh.xtremeRental.User.Role;
import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        User usuario = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String rol = String.valueOf(usuario.getRole());
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .username(usuario.getUsername())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .role(rol)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .nombre(request.getNombre())
            .apellido(request.getApellido())
            .email(request.getEmail())
            .role(Role.USER)
            .build();
        userRepository.save(user);

        User usuario = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String rol = String.valueOf(usuario.getRole());

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
                .username(usuario.getUsername())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .role(rol)
                .build();
        
    }

}
