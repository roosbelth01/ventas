package com.ralva.ventas.service;

import org.springframework.http.HttpHeaders;

import com.ralva.ventas.dto.auth.JwtResponseDTO;
import com.ralva.ventas.dto.auth.LoginDTO;
import com.ralva.ventas.dto.auth.RegisterDTO;
import com.ralva.ventas.dto.auth.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO register(RegisterDTO registerDTO);
    JwtResponseDTO login(LoginDTO loginDTO);
    UsuarioDTO getLoguedUser(HttpHeaders httpHeaders);
}
