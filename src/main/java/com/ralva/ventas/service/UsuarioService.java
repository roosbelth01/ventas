package com.ralva.ventas.service;

import org.springframework.http.HttpHeaders;

import com.ralva.ventas.dto.JwtResponseDTO;
import com.ralva.ventas.dto.LoginDTO;
import com.ralva.ventas.dto.RegisterDTO;
import com.ralva.ventas.dto.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO register(RegisterDTO registerDTO);
    JwtResponseDTO login(LoginDTO loginDTO);
    UsuarioDTO getLoguedUser(HttpHeaders httpHeaders);
}
