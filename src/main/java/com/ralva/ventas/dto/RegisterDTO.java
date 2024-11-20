package com.ralva.ventas.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class RegisterDTO {
    private Long id;
    private String username;
    private String password;
    private Set<RolDTO> roles;
}
