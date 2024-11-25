package com.ralva.ventas.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ralva.ventas.domain.JwtResponseDTO;
import com.ralva.ventas.domain.Rol;
import com.ralva.ventas.domain.Usuario;
import com.ralva.ventas.dto.LoginDTO;
import com.ralva.ventas.dto.RegisterDTO;
import com.ralva.ventas.dto.UsuarioDTO;
import com.ralva.ventas.exception.ConflictException;
import com.ralva.ventas.exception.JwtAuthenticationException;
import com.ralva.ventas.exception.NotFoundException;
import com.ralva.ventas.mapper.auth.UsuarioMapper;
import com.ralva.ventas.repository.UsuarioRepository;
import com.ralva.ventas.security.JwtTokenProvider;
import com.ralva.ventas.service.RolService;
import com.ralva.ventas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolService rolService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolService rolService,
            AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
            PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.rolService = rolService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDTO register(RegisterDTO registerDTO) {
        if (usuarioRepository.existsByUsername(registerDTO.getUsername())) {
            throw new ConflictException("El Usuario ya Exciste!!!");
        }

        Usuario user = usuarioMapper.toUsuarioEntity(registerDTO);
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.getRoles().stream().forEach(e -> {
            Rol rol = rolService.findByName(e.getNombre()).orElseThrow(() -> new NotFoundException("Rol No Encontrado :("));
            e.setId(rol.getId());
        });
        usuarioRepository.save(user);
        UsuarioDTO usuarioDTO = usuarioMapper.toRegisterDto(user);
        return usuarioDTO;
    }

    @Override
    public JwtResponseDTO login(LoginDTO loginDTO) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            return new JwtResponseDTO(token);
        } catch(AuthenticationException e) {
            throw new JwtAuthenticationException("Credenciales invàlidas");
        }
    }

    @Override
    public UsuarioDTO getLoguedUser(HttpHeaders httpHeaders) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = ((User) authentication.getPrincipal()).getUsername();

        Usuario user = usuarioRepository.findByUsername(email)
                .orElseThrow(()-> new NotFoundException("Usuario no encontrado"));
        UsuarioDTO userDto = new UsuarioDTO();
        //userDto.setUsername(user.getUsername());
        userDto.setUsername(user.getUsername());
        //userDto.setRoles(user.getRoles());
        return userDto;
    }
    
}
