package com.ralva.ventas.dto.auth;


import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class UsuarioDTO {
    private Long id;
    private String username;
    private String password;
    private Set<RolDTO> roles;
}
