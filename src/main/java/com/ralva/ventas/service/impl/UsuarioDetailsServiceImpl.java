package com.ralva.ventas.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ralva.ventas.domain.Rol;
import com.ralva.ventas.domain.Usuario;
import com.ralva.ventas.repository.UsuarioRepository;

import java.util.ArrayList;;

@Component
@Transactional
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el username: " + username));
        ArrayList<GrantedAuthority> roles =  new ArrayList<>();
        for(Rol role: user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getNombre()));
        }

        return new User (user.getUsername(), user.getPassword(), roles);
    }
    
}
