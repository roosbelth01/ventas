package com.ralva.ventas.rest.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ralva.ventas.domain.JwtResponseDTO;
import com.ralva.ventas.dto.LoginDTO;
import com.ralva.ventas.dto.RegisterDTO;
import com.ralva.ventas.dto.UsuarioDTO;
import com.ralva.ventas.security.JwtTokenProvider;
//import com.ralva.ventas.service.RolService;
import com.ralva.ventas.service.UsuarioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService userService;
    //private final RolService rolService;
    private final JwtTokenProvider jwtGenerator;


    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginDTO loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        userService.register(registerDto);

        return new ResponseEntity<>("User register success!", HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToke(Authentication authentication){

        String token = jwtGenerator.refreshToken(authentication);

        JwtResponseDTO jwtRefresh = new JwtResponseDTO(token);
        return new ResponseEntity<JwtResponseDTO>(jwtRefresh, HttpStatus.OK);
    }

    @GetMapping("/logued")
    public ResponseEntity<UsuarioDTO> getLoguedUser(@RequestHeader HttpHeaders headers){
        return new ResponseEntity<>(userService.getLoguedUser(headers), HttpStatus.OK);
    }
}
