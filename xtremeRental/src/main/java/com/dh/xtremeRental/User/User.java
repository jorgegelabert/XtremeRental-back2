package com.dh.xtremeRental.User;

import com.dh.xtremeRental.entity.Alquiler;
import com.dh.xtremeRental.entity.Favorito;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue
    Integer id;
    @Basic
    @NotBlank
    @Size(max=30)
    String username;
    @NotBlank
    String apellido;
    @NotBlank
    String nombre;
    @Email
    @NotBlank
    @Size(max=80)
    String email;
    @NotBlank
    String password;
    @Enumerated(EnumType.STRING) 
    Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Alquiler> alquileres = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Favorito> favoritos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.name())));
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void cambiarRol(Role nuevoRol) {
        this.role = nuevoRol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, apellido, nombre, email, password, role);
    }
}
